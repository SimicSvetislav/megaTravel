import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { RezervacijaKorisnika } from 'src/app/model/rezervacija/rezervacija-korisnika.model';

@Component({
  selector: 'app-view-booking',
  templateUrl: './view-booking.component.html',
  styleUrls: ['./view-booking.component.css']
})
export class ViewBookingComponent implements OnInit {

  @Input()
  selectedBooking: RezervacijaKorisnika;

  @Output()
  back = new EventEmitter();

  @Output()
  confirm = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  confirmBook() {
    this.confirm.emit();
  }

}
