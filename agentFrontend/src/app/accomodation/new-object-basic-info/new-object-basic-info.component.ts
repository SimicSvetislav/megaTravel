import { Polozaj } from './../../model/smestaj/polozaj.model';
import { Lokacija } from './../../model/smestaj/lokacija.model';
import { Koordinate } from './../../model/smestaj/koordinate.model';
import { AccomodationService } from './../../service/accomodation.service';
import { KategorijaSmestaja } from './../../model/smestaj/kategorija-smestaja.model';
import { TipSmestaja } from './../../model/smestaj/tip-smestaja.model';
import { SmestajniObjekat } from 'src/app/model/smestaj/smestajni-objekat.model';
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Otkazivanje } from 'src/app/model/smestaj/otkazivanje.model';

@Component({
  selector: 'app-new-object-basic-info',
  templateUrl: './new-object-basic-info.component.html',
  styleUrls: ['./new-object-basic-info.component.css']
})
export class NewObjectBasicInfoComponent implements OnInit {

  @Input()
  object: SmestajniObjekat;

  @Output()
  addBasicInfo = new EventEmitter();

  @Output()
  backBasicInfo = new EventEmitter();

  // select opcije
  types: TipSmestaja[];
  categories: KategorijaSmestaja[];


  tip: TipSmestaja;
  kategorija: KategorijaSmestaja;
  naziv: string;
  opisObjekta: string;

  adresa: string;

  duzinaStepeni: string;
  sirinaStepeni: string;

  duzinaOrijentacija: string;
  sirinaOrijentacija: string;

  dozvoljenoOtkazivanje: boolean;
  brojDanaOtkazivanja: string;
  faktorOtkazivanja: string;

  constructor(private accomodationService: AccomodationService, private router: Router) { }

  ngOnInit() {
    // const t: TipSmestaja[] = new Array();
    // const s: TipSmestaja = new TipSmestaja(1, 'hotel');
    // s.id = 1;
    // s.naziv = 'hotel';

    // const s2: TipSmestaja = new TipSmestaja(2, 'hotel');
    // s2.id = 1;
    // s2.naziv = 'bed and breakfast';

    // const s3: TipSmestaja = new TipSmestaja(3, 'hotel');
    // s3.id = 1;
    // s3.naziv = 'apartman';

    // const s4: TipSmestaja = new TipSmestaja(4, 'hotel');
    // s4.id = 1;
    // s4.naziv = 'aprathotel';

    // t.push(s);
    // t.push(s2);
    // t.push(s3);
    // t.push(s4);

    // this.categories = [{ id: 1, zvezdice: 8}, {id: 1, zvezdice: 8}, {id: 1, zvezdice: 1}];

    this.accomodationService.getObjectCategories().subscribe(data => {
      this.categories = data;
    });

    this.accomodationService.getObjectTypes().subscribe(data => {
      this.types = data;
    });

    // this.types = t;
    this.dozvoljenoOtkazivanje = false;
  }

  back() {
    this.backBasicInfo.emit();
  }

  next(f: NgForm) {

    if (f.form.status === 'INVALID') {
      alert('Molimo popunite obavezne paremetre[NEADEKVATAN UNOS]');
      return;
    }

    const lokacija: Lokacija = new Lokacija() ;
    lokacija.naziv = this.adresa;
    if (this.duzinaStepeni && this.sirinaStepeni) {
      const sirina: Koordinate = new Koordinate();
      sirina.stepeni = +this.sirinaStepeni;
      sirina.strana  = this.sirinaOrijentacija;

      const duzina: Koordinate = new Koordinate();
      duzina.stepeni = +this.duzinaStepeni;
      duzina.strana = this.duzinaOrijentacija;

      const polozaj: Polozaj = new Polozaj();
      polozaj.geoDuzina = +this.duzinaStepeni;
      polozaj.geoSirina = +this.sirinaStepeni;

      lokacija.koordinate = polozaj;
    }
    this.object.lokacija = lokacija;


    const otkazivanje: Otkazivanje = new Otkazivanje();
    otkazivanje.dozvoljeno = this.dozvoljenoOtkazivanje;
    otkazivanje.brojDana = this.brojDanaOtkazivanja === undefined ? 0 : +this.brojDanaOtkazivanja;
    if (!otkazivanje.dozvoljeno && otkazivanje.brojDana > 0 ) {
      otkazivanje.brojDana = 0;

    }

    this.object.tipSmestaja = this.tip;
    this.object.kategorijaSm = this.kategorija;
    this.object.opis = this.opisObjekta;

    this.addBasicInfo.emit();
  }

}
