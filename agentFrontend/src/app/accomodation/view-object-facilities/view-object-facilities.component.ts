import { DodatnaUsluga } from './../../model/smestaj/dodatna-usluga.model';
import { Component, OnInit, Input } from '@angular/core';
import { SmestajniObjekat } from 'src/app/model/smestaj/smestajni-objekat.model';

@Component({
  selector: 'app-view-object-facilities',
  templateUrl: './view-object-facilities.component.html',
  styleUrls: ['./view-object-facilities.component.css']
})
export class ViewObjectFacilitiesComponent implements OnInit {

  @Input()
  object: SmestajniObjekat;
  facilities: DodatnaUsluga[] = [];

  constructor() { }

  ngOnInit() {
    this.facilities = [{id: 1, ime: 'Wifi', cena: undefined, jedinicaPlacanja: undefined, smestajniObjekat: undefined},
    {id: 5, ime: 'Parking', cena: undefined, jedinicaPlacanja: undefined, smestajniObjekat: undefined},
    {id: 2, ime: 'Room service', cena: undefined, jedinicaPlacanja: undefined, smestajniObjekat: undefined},
    {id: 3, ime: 'Pansion', cena: undefined, jedinicaPlacanja: undefined, smestajniObjekat: undefined},
    {id: 4, ime: 'Kucni ljubimci', cena: undefined, jedinicaPlacanja: undefined, smestajniObjekat: undefined}, ];
  }

}
