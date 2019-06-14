import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { SmestajniObjekat } from 'src/app/model/smestaj/smestajni-objekat.model';
import { Cenovnik } from 'src/app/model/smestaj/cenovnik.model';
import { NgForm } from '@angular/forms';
import { Cena } from 'src/app/model/smestaj/cena.model';

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

  start: Date;
  end: Date;
  valuta: string;
  iznos: string;

  selectedIndex: number;

  shouldPriceFormDisplay: boolean;

  constructor() { }

  ngOnInit() {
    // this.dodatiCenovnici = [
    //   {pocetak: new Date(), kraj: new Date(), cena : {iznos: 255, valuta: 'RSD'}, smestaj: new SmestajnaJedinica(), pocetakStr: '',
    //   krajStr: ''},
    //   {pocetak: new Date(), kraj: new Date(), cena : {iznos: 255, valuta: 'RSD'}, smestaj: new SmestajnaJedinica(), pocetakStr: '',
    //   krajStr: ''},
    //   {pocetak: new Date(), kraj: new Date(), cena : {iznos: 255, valuta: 'RSD'}, smestaj: new SmestajnaJedinica(), pocetakStr: '',
    //   krajStr: ''},
    //   {pocetak: new Date(), kraj: new Date(), cena : {iznos: 255, valuta: 'RSD'}, smestaj: new SmestajnaJedinica(), pocetakStr: '',
    //   krajStr: ''},
    // ];
  }

  back() {
    this.pricelistBack.emit();
  }

  add() {
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

    const novaCena: Cena = new Cena(this.valuta, num);
    const noviCenovnik: Cenovnik = new Cenovnik();
    noviCenovnik.cena = novaCena;
    noviCenovnik.pocetak = this.start;
    noviCenovnik.kraj = this.end;
    noviCenovnik.pocetakStr = b;
    noviCenovnik.krajStr = e;
    this.dodatiCenovnici.push(noviCenovnik);

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
      const retVal1: boolean = this.compareTwoDates(this.end, cenovnik.pocetak);
      const retVal2: boolean = this.compareTwoDates(cenovnik.kraj, this.start);
      if (!retVal1 || !retVal2) {
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
