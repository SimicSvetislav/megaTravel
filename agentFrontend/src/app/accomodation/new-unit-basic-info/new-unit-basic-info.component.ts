import { NgForm } from '@angular/forms';
import { Otkazivanje } from './../../model/smestaj/otkazivanje.model';
import { SmestajniObjekat } from 'src/app/model/smestaj/smestajni-objekat.model';
import { SmestajnaJedinica } from './../../model/smestaj/smestajna-jedinica.model';
import { AccomodationService } from './../../service/accomodation.service';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-new-unit-basic-info',
  templateUrl: './new-unit-basic-info.component.html',
  styleUrls: ['./new-unit-basic-info.component.css']
})
export class NewUnitBasicInfoComponent implements OnInit {

  @Input()
  unit: SmestajnaJedinica;

  @Input()
  object: SmestajniObjekat;

  @Output()
  addBasicInfo = new EventEmitter();

  otkazivanje: boolean;
  brojDanaOtkazivanja: string;
  faktorOtkazivanja: string;

  naziv: string;

  constructor(private accomodationService: AccomodationService) { }

  ngOnInit() {
  }

  back() {

  }

  next(f: NgForm) {
    if (f.form.status === 'INVALID') {
      alert('Molimo popunite obavezne paremetre[NEADEKVATAN UNOS]');
      return;
    }

    if (this.otkazivanje && this.brojDanaOtkazivanja && this.faktorOtkazivanja) {
      const otkazivanje: Otkazivanje = new Otkazivanje();
      otkazivanje.brojDana = +this.brojDanaOtkazivanja;
      otkazivanje.faktorOtkazivanja = +this.faktorOtkazivanja;
      otkazivanje.dozvoljeno = this.otkazivanje;
      this.object.otkazivanje = otkazivanje;
    }

    console.log(this.otkazivanje);
    this.unit.brojKreveta = +this.unit.brojKreveta;


    this.addBasicInfo.emit();
  }

}
