import { AccomodationService } from 'src/app/service/accomodation.service';
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

  constructor(private accomodationService: AccomodationService) { }

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
    this.dodatneUsluge = this.object.dodatnaUsluga;

    // this.facilities = [{id: 1, ime: 'Wifi', cena: undefined, jedinicaPlacanja: undefined},
    // {id: 2, ime: 'Parking', cena: undefined, jedinicaPlacanja: undefined},
    // {id: 3, ime: 'Room service', cena: undefined, jedinicaPlacanja: undefined},
    // {id: 4, ime: 'Pansion', cena: undefined, jedinicaPlacanja: undefined},
    // {id: 5, ime: 'Kucni ljubimci', cena: undefined, jedinicaPlacanja: undefined}, ];

    this.accomodationService.getExtras().subscribe(data => {
      this.facilities = data;

      // inicijalizacija
      this.selectedRows = new Array(this.facilities.length);
      for (let i = 0; i < this.facilities.length; ++i) {
        this.selectedRows[i] = false;
      }

      // ukoliko je vec neka usluga selektovana oboji pozadinu
      for (let i = 0; i < this.facilities.length; ++i) {
        const usluga: DodatnaUsluga[] = this.object.dodatnaUsluga.filter(u => u.id === this.facilities[i].id);
        if (usluga.length > 0) {
          this.selectedRows[i] = true;
        }
      }
    });



  }

  addFacilities() {
    if (this.object) {
      this.object.dodatnaUsluga = this.dodatneUsluge;
    }

    // if (this.unit) {
    //    this.unit.dodatneUsluge = this.dodatneUsluge;
    // }
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
    // uklanjanje iz selektovanih usluga
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
