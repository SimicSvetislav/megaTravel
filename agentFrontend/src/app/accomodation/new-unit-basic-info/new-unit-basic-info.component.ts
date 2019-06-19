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

  @Output()
  backBasicInfo = new EventEmitter();

  balkon: boolean;
  brojKreveta: number;
  dozvoljenoOtkazivanje: boolean;
  brojDanaOtkazivanja: number;
  faktorOtkazivanja: string;

  naziv: string;

  constructor(private accomodationService: AccomodationService) { }

  ngOnInit() {
    this.dozvoljenoOtkazivanje = false;
    this.balkon = false;
  }

  back() {
    this.backBasicInfo.emit();
  }

  next(f: NgForm) {
    if (f.form.status === 'INVALID') {
      alert('Molimo popunite obavezne paremetre[NEADEKVATAN UNOS]');
      return;
    }

    const otkazivanje: Otkazivanje = new Otkazivanje();
    otkazivanje.dozvoljeno = this.dozvoljenoOtkazivanje;
    // otkazivanje.dozvoljeno = this.dozvoljenoOtkazivanje === undefined ? false : true;
    otkazivanje.brojDana = this.brojDanaOtkazivanja === undefined ? 0 : +this.brojDanaOtkazivanja;
    if (!otkazivanje.dozvoljeno && otkazivanje.brojDana > 0 ) {
      otkazivanje.brojDana = 0;

    }

    console.log(this.dozvoljenoOtkazivanje);
    // this.balkon = this.balkon === undefined ? false : true;
    this.brojKreveta = +this.brojKreveta;
    // this.unit.id = null;
   // this.unit = new SmestajnaJedinica(null, this.brojKreveta, this.balkon, this.object.id, otkazivanje);
    this.unit.balkon = this.balkon;
    this.unit.brojKreveta = this.brojKreveta;
    this.unit.otkazivanje = otkazivanje;

    this.addBasicInfo.emit();
  }

}
