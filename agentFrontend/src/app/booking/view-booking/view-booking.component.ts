import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { RezervacijaKorisnika } from 'src/app/model/rezervacija/rezervacija-korisnika.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-booking',
  templateUrl: './view-booking.component.html',
  styleUrls: ['./view-booking.component.css']
})
export class ViewBookingComponent implements OnInit {

  @Input()
  selectedBooking: RezervacijaKorisnika;

  @Input()
  bookingType: any;

  @Output()
  back = new EventEmitter();

  @Output()
  confirm = new EventEmitter();

  constructor(private router: Router) { }

  ngOnInit() {
  }

  confirmBook() {
    this.confirm.emit();
  }

  startChat() {
    this.router.navigate(['chat/' + this.selectedBooking.id]);
  }

}
