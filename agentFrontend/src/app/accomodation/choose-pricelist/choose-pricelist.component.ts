import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { SmestajniObjekat } from 'src/app/model/smestaj/smestajni-objekat.model';
import { Cenovnik } from 'src/app/model/smestaj/cenovnik.model';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-choose-pricelist',
  templateUrl: './choose-pricelist.component.html',
  styleUrls: ['./choose-pricelist.component.css']
})
export class ChoosePricelistComponent implements OnInit {

  @Input()
  unit: SmestajnaJedinica;

  @Input()
  object: SmestajniObjekat;

  @Output()
  pricelistBack = new EventEmitter();

  @Output()
  addPricelist = new EventEmitter();

  dodatiCenovnici: Cenovnik[] = [];
  podrazumevaniCenovnik: Cenovnik;
  podrazumevanaCena: number;

  start: Date;
  end: Date;
  valuta: string;
  iznos: string;

  selectedIndex: number;

  shouldPriceFormDisplay: boolean;

  constructor() { }

  ngOnInit() {
    this.dodatiCenovnici = this.unit.cenovnici;
    // this.podrazumevaniCenovnik = this.object.podrazumevaniCenovnik;
    // this.podrazumevaniCenovnik = undefined;
    this.podrazumevanaCena = this.unit.podrazumevanaCena;
  }

  back() {
    this.pricelistBack.emit();
  }

  add() {
    if (this.podrazumevaniCenovnik === undefined && this.dodatiCenovnici.length === 0) {
      alert('Morate uneti bar jedan cenovnik');
      return;
    }

    // this.object.cenovnici = this.dodatiCenovnici;
    // this.object.podrazumevaniCenovnik = this.podrazumevaniCenovnik;
    this.unit.cenovnici = this.dodatiCenovnici;
    this.unit.podrazumevanaCena = this.podrazumevaniCenovnik.cena;
    this.addPricelist.emit();
  }

  defaultPriceChanged() {
    const order: number = +this.selectedIndex;
    this.podrazumevaniCenovnik = this.dodatiCenovnici[order - 1];
  }

  addNewPrice(f: NgForm) {
    if (f.form.status === 'INVALID') {
      alert('Popunite obavezne parametre');
      return;
    }

    const num: number = parseFloat(this.iznos);
    if (isNaN(num)) {
      alert('Iznos mora biti broj.');
      return;
    }

    if (!this.validateDates()) {
      return;
    }

    let trueMonth = ('0' + this.start['month']).slice(-2);
    let trueDay = ('0' + this.start['day']).slice(-2);
    const b = this.start['year'] + '-' + trueMonth + '-' + trueDay;

    trueMonth = ('0' + this.end['month']).slice(-2);
    trueDay = ('0' + this.end['day']).slice(-2);
    const e = this.end['year'] + '-' + trueMonth + '-' + trueDay;

    const novaCena: number =  num;
    const noviCenovnik: Cenovnik = new Cenovnik();
    noviCenovnik.cena = novaCena;
    noviCenovnik.pocetakDateType = this.start;
    noviCenovnik.krajDateType = this.end;
    noviCenovnik.pocetak = b;
    noviCenovnik.kraj = e;
    this.dodatiCenovnici.push(noviCenovnik);
    if (this.dodatiCenovnici.length === 1) {
      this.podrazumevaniCenovnik = noviCenovnik;
    }

    this.start = undefined;
    this.end = undefined;
    this.iznos = undefined;
    this.valuta = undefined;

    this.shouldPriceFormDisplay = !this.shouldPriceFormDisplay;
  }

  quit() {
    this.shouldPriceFormDisplay = !this.shouldPriceFormDisplay;
  }

  validateDates(): boolean {

    if (!this.compareTwoDates(this.start, this.end)) {
      alert('Datum pocetka je veci od datuma zavrsetka');
      return false;
    }

    // da se izbegne poklapanje nekih termina
    for (const cenovnik of this.dodatiCenovnici) {
      // cenovnik.pocetak
      const retValB1B2: boolean = this.compareTwoDates(this.start, cenovnik.pocetakDateType);
      const retValB1E2: boolean = this.compareTwoDates(this.start, cenovnik.krajDateType);
      const retValB2B1: boolean = this.compareTwoDates(cenovnik.pocetakDateType, this.start);
      const retValB2E1: boolean = this.compareTwoDates(cenovnik.pocetakDateType, this.end);

      let retVal1: boolean;
      if (!retValB1B2 && !retValB1E2) {
        retVal1 = false;
      } else {
        retVal1 = true;
      }
      let retVal2: boolean;
      if (!retValB2B1 && !retValB2E1) {
        retVal2 = false;
      } else {
        retVal2 = true;
      }

      if (!retVal1 || !retVal2) {
        console.log('Nema poklapanja intervala');
      } else {
        alert('Datum pocetka je veci od datuma zavrsetka');
        return false;
      }
    }
    return true;
  }

  compareTwoDates(date1, date2) {
    const pG: number = date1['year'];
    const pM: number = date1['month'];
    const pD: number = date1['day'];
    const dG: number = date2['year'];
    const dM: number = date2['month'];
    const dD: number = date2['day'];

    if ( pG > dG) {
      // alert('Datum pocetka je veci od datuma zavrsetka');
      return false;
    }

    if ( pM > dM && pG === dG) {
      // alert('Datum pocetka je veci od datuma zavrsetka');
      return false;
    }

    if ( pD > dD && pM === dM) {
      // alert('Datum pocetka je veci od datuma zavrsetka');
      return false;
    }

    return true;
  }

}
