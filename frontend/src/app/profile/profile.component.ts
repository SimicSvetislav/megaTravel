import { UserService } from './../services/user/user.service';
import { Component, OnInit } from '@angular/core';
import { RezervacijaKorisnika } from '../rezervacijaKorisnika';
import { TokenStorageService } from '../services/auth/token-storage.service';
import { SmestajniObjekat } from '../smestajniObjekat';
import { DatePipe } from '@angular/common';
import * as moment from 'moment';
import { Route, Router } from '@angular/router';

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

  constructor(private userService: UserService,
              private tokenService: TokenStorageService,
              private datePipe: DatePipe,
              private router: Router) { }

  ngOnInit() {

    this.id = this.tokenService.getUser();

    if(this.id == null) {
      this.boolLogIn = false;
      this.boolLogOff = true;
    } else {
      this.boolLogIn = true;
      this.boolLogOff = false;
      
    }
   
    if(this.id == null) {
      alert("Ne postoji ulogovan korisnik!");
      this.router.navigate(["/home"]);
    }
    this.today = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
    alert("ID: " + this.id)

    this.userService.getAllReservationsById(this.id).subscribe(data => {
        this.reservations = data;

        for(let r of this.reservations) {
          var now = moment(this.today); 
          var end = moment(r.datumZavrsetka); 
          var duration = moment.duration(end.diff(now));
          this.days = duration.asDays();
          alert("days : " + this.days)
          if(this.days < 0) {
            this.reservationsOutOfDate.push(r)
          } else {
            this.reservationsActive.push(r);
          }

          
          
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

}
