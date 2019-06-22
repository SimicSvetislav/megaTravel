import { UserService } from '../services/users/user.service';
import { Component, OnInit } from '@angular/core';
import { RezervacijaKorisnika } from '../rezervacijaKorisnika';
import { TokenStorageService } from '../services/auth/token-storage.service';
import { SmestajniObjekat } from '../smestajniObjekat';
import { DatePipe } from '@angular/common';
import * as moment from 'moment';
import { Route, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SmestajnaJedinica } from '../smestajnaJedinica';

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
    private modalService: NgbModal) { }

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

  sendKomentar(komentar) {

  }

  oceni(idSobe,ocena,userId) {
    this.userService.setRate(idSobe,ocena,userId);
  }

  html(id: number) {
    this.router.navigate(['/reservation/report/' + id]);
  }

}
