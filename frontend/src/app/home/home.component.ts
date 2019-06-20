import { User } from './../user';
import { TokenStorageService } from './../services/auth/token-storage.service';
import { Component, OnInit, Output } from '@angular/core';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { EventEmitter } from 'events';
import { EventBrokerService } from '../services/event-broker/event-broker.service';




@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  @Output() refresh = new EventEmitter();

  fromDate: NgbDate;
  toDate: NgbDate;
  hoveredDate: NgbDate;
  user: User = new User();

  id;
  boolLogIn: boolean = false;
  boolLogOff: boolean = false;

  constructor(private eventBroker: EventBrokerService, private router: Router,private token: TokenStorageService) { }

  ngOnInit() {

    if (this.token.getRefresh()==='true') {
      this.token.saveRefresh(false);
      this.eventBroker.myEmit("refresh");
    }

    this.id = this.token.getUser();

    if(this.id == null) {
      this.boolLogIn = false;
      this.boolLogOff = true;
    } else {
      this.boolLogIn = true;
      this.boolLogOff = false;
    }


  }



  onDateSelection(date: NgbDate) {
    if (!this.fromDate && !this.toDate) {
      this.fromDate = date;
    } else if (this.fromDate && !this.toDate && date.after(this.fromDate)) {
      this.toDate = date;
    } else {
      this.toDate = null;
      this.fromDate = date;
    }
  }

  isHovered(date: NgbDate) {
    return this.fromDate && !this.toDate && this.hoveredDate && date.after(this.fromDate) && date.before(this.hoveredDate);
  }

  isInside(date: NgbDate) {
    return date.after(this.fromDate) && date.before(this.toDate);
  }

  isRange(date: NgbDate) {
    return date.equals(this.fromDate) || date.equals(this.toDate) || this.isInside(date) || this.isHovered(date);
  }

  signOut() {
    this.eventBroker.myEmit("refresh");
    this.token.signOut();
    this.router.navigate(['/login']);
  }

  login() {
    this.router.navigate(['/login'])
  }

  register() {
    this.router.navigate(['/register'])
  }

}
