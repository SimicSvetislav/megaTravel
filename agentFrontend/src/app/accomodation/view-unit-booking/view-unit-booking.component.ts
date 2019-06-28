import { ToastrService } from 'ngx-toastr';
import { BookingService } from 'src/app/service/booking.service';
import { Component, OnInit, Input } from '@angular/core';
import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { NgForm } from '@angular/forms';
import { Rezervacija } from 'src/app/model/rezervacija/rezervacija.model';
import { RezervacijaKorisnika } from 'src/app/model/rezervacija/rezervacija-korisnika.model';
import { HttpResponse } from '@angular/common/http/http';

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

  rezervacijeZaSobu: RezervacijaKorisnika[] = [];

  constructor(private bookingService: BookingService, private toastrService: ToastrService) { }

  ngOnInit() {
    this.isCollapsed = true;
    this.bookingService.getAllBookingsByUnit(this.unit.id.toString()).subscribe(data => {
      this.rezervacijeZaSobu = data;
      this.rezervacijeZaSobu.sort((r2, r1) =>  r2.id - r1.id );
    });


  }

  quit() {
    this.start = undefined;
    this.start = undefined;
    this.isCollapsed = !this.isCollapsed;
  }

  bookUnit(f: NgForm) {
    if (!f.form.valid) {
      this.toastrService.warning('Unesite potrebne datume');
      return;
    }

    const dates: string[] = this.parseDate();
    const rez: RezervacijaKorisnika = new RezervacijaKorisnika(null, dates[0], dates[1], this.unit.id, 0, '', -1,  'REZERVISANO', -1);

    this.bookingService.makeBooking(rez).subscribe(data => {
      this.isCollapsed = !this.isCollapsed;
      this.start = undefined;
      this.end = undefined;
      this.ngOnInit();
    }, (error: Response) => {
      if (error.status === 409) {
        this.toastrService.error('Soba je zauzeta za uneti termin! Molimo pokusajte sa nekim drugim terminom');
      }
    });

  }

  parseDate(): string[] {
    let trueMonth = ('0' + this.start['month']).slice(-2);
    let trueDay = ('0' + this.start['day']).slice(-2);
    const b = this.start['year'] + '-' + trueMonth + '-' + trueDay;

    trueMonth = ('0' + this.end['month']).slice(-2);
    trueDay = ('0' + this.end['day']).slice(-2);
    const e = this.end['year'] + '-' + trueMonth + '-' + trueDay;
    return [b, e];
  }

}
