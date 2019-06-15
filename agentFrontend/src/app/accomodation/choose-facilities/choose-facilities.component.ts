import { DodatnaUsluga } from './../../model/smestaj/dodatna-usluga.model';
import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { SmestajniObjekat } from 'src/app/model/smestaj/smestajni-objekat.model';
import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { OuterSubscriber } from 'rxjs/internal/OuterSubscriber';

@Component({
  selector: 'app-choose-facilities',
  templateUrl: './choose-facilities.component.html',
  styleUrls: ['./choose-facilities.component.css']
})
export class ChooseFacilitiesComponent implements OnInit {

  constructor() { }

  @Input()
  object: SmestajniObjekat;

  @Input()
  unit: SmestajnaJedinica;

  @Output()
  chooseFacilities = new EventEmitter();

  @Output()
  backFacilities = new EventEmitter();

  selectedRows: boolean[];

  dodatneUsluge: DodatnaUsluga[];
  facilities: DodatnaUsluga[];

  ngOnInit() {
    this.dodatneUsluge = [];

    this.facilities = [{id: 1, ime: 'Wifi', cena: undefined, jedinicaPlacanja: undefined, smestajniObjekat: undefined},
    {id: 5, ime: 'Parking', cena: undefined, jedinicaPlacanja: undefined, smestajniObjekat: undefined},
    {id: 2, ime: 'Room service', cena: undefined, jedinicaPlacanja: undefined, smestajniObjekat: undefined},
    {id: 3, ime: 'Pansion', cena: undefined, jedinicaPlacanja: undefined, smestajniObjekat: undefined},
    {id: 4, ime: 'Kucni ljubimci', cena: undefined, jedinicaPlacanja: undefined, smestajniObjekat: undefined}, ];

    this.selectedRows = new Array(this.facilities.length);
    for (let i = 0; i < this.facilities.length; ++i) {
      this.selectedRows[i] = false;
    }

  }

  addFacilities() {
    if (this.object) {
      // this.object.dodatneUsluge = this.dodatneUsluge;
    }

    if (this.unit) {
      // this.unit.dodatneUsluge = this.dodatneUsluge;
    }
    this.chooseFacilities.emit();
  }

  back() {
    if (this.object) {
      // this.object.dodatneUsluge = this.dodatneUsluge;
    }

    if (this.unit) {
      // this.unit.dodatneUsluge = this.dodatneUsluge;
    }
    this.backFacilities.emit();
  }

  ukloniUslugu(usluga: DodatnaUsluga, i: number) {
    const index = this.dodatneUsluge.indexOf(usluga);
    this.dodatneUsluge.splice(index, 1);
    this.selectedRows[i] = false;
  }

  dodajUslugu(usluga: DodatnaUsluga, i: number) {
    const existFacility: DodatnaUsluga[] =  this.dodatneUsluge.filter( u => u.id === usluga.id);
    if (existFacility.length === 0) {
     this.dodatneUsluge.push(usluga);
     this.selectedRows[i] = true;
    }
  }

}
