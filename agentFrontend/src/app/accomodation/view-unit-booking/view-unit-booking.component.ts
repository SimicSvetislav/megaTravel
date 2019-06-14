import { Component, OnInit, Input } from '@angular/core';
import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { NgForm } from '@angular/forms';
import { Rezervacija } from 'src/app/model/rezervacija/rezervacija.model';
import { RezervacijaKorisnika } from 'src/app/model/rezervacija/rezervacija-korisnika.model';

@Component({
  selector: 'app-view-unit-booking',
  templateUrl: './view-unit-booking.component.html',
  styleUrls: ['./view-unit-booking.component.css']
})
export class ViewUnitBookingComponent implements OnInit {

  @Input()
  unit: SmestajnaJedinica;

  rezervacije: RezervacijaKorisnika;

  isCollapsed: boolean;
  start: Date;
  end: Date;


  constructor() { }

  ngOnInit() {
    this.isCollapsed = true;
  }

  quit() {
    this.start = undefined;
    this.start = undefined;
    this.isCollapsed = !this.isCollapsed;
  }

  bookUnit(f: NgForm) {
    if (f.form.valid) {
      alert('Unesite potrebne datume');
      return;
    }
    this.isCollapsed = !this.isCollapsed;
  }

}
