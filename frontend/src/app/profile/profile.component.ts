import { ObjectService } from './../services/object/object.service';
import { UserService } from '../services/users/user.service';
import { Component, OnInit } from '@angular/core';
import { RezervacijaKorisnika } from '../rezervacijaKorisnika';
import { TokenStorageService } from '../services/auth/token-storage.service';
import { SmestajniObjekat } from '../smestajniObjekat';
import { DatePipe } from '@angular/common';
import * as moment from 'moment';
import { Route, Router, ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SmestajnaJedinica } from '../smestajnaJedinica';
import { Rating } from '../rating';
import { ReservationService } from '../services/reservations/reservation.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {


  reservations: RezervacijaKorisnika[] = [];
  reservationsActive: RezervacijaKorisnika[] = [];
  reservationsOutOfDate: RezervacijaKorisnika[] = [];
  id;
  today: string;
  days;
  cancelFlag: boolean = true;
  boolLogIn: boolean = false;
  boolLogOff: boolean = false;

  komentar: string = "";
  ocenaTemp;

  sj: SmestajnaJedinica = new SmestajnaJedinica();

  constructor(private userService: UserService,
    private tokenService: TokenStorageService,
    private datePipe: DatePipe,
    private router: Router,
    private modalService: NgbModal,
    private reservService: ReservationService,
    private objService: ObjectService,
    private route: ActivatedRoute) { }

  ngOnInit() {

    var user = this.tokenService.getUser();

    if (user==null) {
      this.router.navigate(['/home']);
    }

    this.id = this.tokenService.getUser();

    if (this.id == null) {
      this.boolLogIn = false;
      this.boolLogOff = true;
    } else {
      this.boolLogIn = true;
      this.boolLogOff = false;
    }

    if (this.id == null) {
      alert("Ne postoji ulogovan korisnik!");
      this.router.navigate(["/home"]);
    }
    this.today = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
    //alert("ID: " + this.id)

    this.userService.getAllReservationsById(this.id).subscribe(data => {
      this.reservations = data;
      //alert("ALERT ")
      for (let r of this.reservations) {
        var now = moment(this.today);
        var end = moment(r.datumZavrsetka);
        var duration = moment.duration(end.diff(now));
        this.days = duration.asDays();
       // alert("days : " + this.days)
       //alert("OCenjeno: " + r.ocenjeno);
        if (this.days < 0) {
          this.reservationsOutOfDate.push(r);
          
        } else {
          this.reservationsActive.push(r);
        /*  this.objService.getObjectOfUnit(r.id).subscribe(data => { 
            r.smestajniObjekat = data;           
          })*/
        }

       /* this.userService.getSmestajnaJedinica(r.smestajnaJedinica).subscribe(data => {
            this.sj = data;
            //neka ideja da se vrati Smestajna jedinica konkretne rezervacije, 
            //al ne znam jel to potrebno ovde
        })*/
      }

    })

  
  }

  getall() {
    this.userService.getAllReservationsById(this.id).subscribe(data => {
      this.reservations = data;
      //alert("ALERT ")
      for (let r of this.reservations) {
        var now = moment(this.today);
        var end = moment(r.datumZavrsetka);
        var duration = moment.duration(end.diff(now));
        this.days = duration.asDays();
       // alert("days : " + this.days)
       //alert("OCenjeno: " + r.ocenjeno);
        if (this.days < 0) {
          this.reservationsOutOfDate.push(r)
        } else {
          this.reservationsActive.push(r);
        }

       /* this.userService.getSmestajnaJedinica(r.smestajnaJedinica).subscribe(data => {
            this.sj = data;
            //neka ideja da se vrati Smestajna jedinica konkretne rezervacije, 
            //al ne znam jel to potrebno ovde
        })*/
      }

    })
  }


  signOut() {
    this.tokenService.signOut();
    this.router.navigate(['/login']);
  }

  login() {
    this.router.navigate(['/login'])
  }

  register() {
    this.router.navigate(['/register'])
  }

  chat(res: number) {
    this.router.navigate(['/chat/' + res]);
  }

  openVerticallyCentered(content) {
    this.modalService.open(content, {backdropClass: 'light-blue-backdrop'});
  }

  

  idSobe;
  userId;
  ocena;
  idObjekta;
  idTempRes; //id rezervacije koja se ocenjuje trenutno
  tempRes: RezervacijaKorisnika = new RezervacijaKorisnika();
  tempSj: SmestajnaJedinica = new SmestajnaJedinica();
  r: Rating = new Rating();

  oceni(idSobe,ocena,content,idd) {
    this.modalService.open(content, {backdropClass: 'light-blue-backdrop'});
    this.idSobe = idSobe;
    this.userId = this.id;
    this.ocena = ocena;
    this.idTempRes = idd;
    
    this.objService.getUnitById(this.idSobe).subscribe(data => {
      this.tempSj = data;
      this.idObjekta = this.tempSj.sobjekat;
    })
  }

  izmeniRes() {
    //alert("Usao")
    this.reservService.getReservation(this.idTempRes).subscribe(data => {
      this.tempRes = data;
     // alert("Getovao: " + this.tempRes.id);
      this.tempRes.ocenjeno = true;

      this.reservService.putReservation(this.tempRes).subscribe(data => {
      //  alert("Putovao!")
          window.location.reload();
      });

    })
  }

  sendOcena() {
    this.modalService.dismissAll();
    /*this.r.grade = this.ocena;
    this.r.user = this.userId;
    this.r.room = this.idSobe;

    this.userService.setRate(this.r).subscribe(data => {
     // alert("ALLLL")
      console.log(data);
      this.izmeniRes();
     
      
    });*/

    
  }

  sendKomentarIocena(komentar) {
    this.modalService.dismissAll();
    this.r.grade = this.ocena;
    this.r.user = this.userId;
    this.r.room = this.idSobe;
    this.r.comment = this.komentar;
    this.r.object = this.idObjekta;
    
    this.userService.setRate(this.r).subscribe(data => {
      console.log(data);
      this.izmeniRes();
      
    });
  }

  html(id: number) {
    this.router.navigate(['/reservation/report/' + id]);
  }

  cancel(id: number, pocetak: Date) {

    this.reservService.cancel(id).subscribe( data => {
      this.getall();
      alert(data);
    }, error => console.log(error));

  }

  
}
