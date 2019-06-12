import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { SmestajniObjekat } from 'src/app/model/smestaj/smestajni-objekat.model';

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

  constructor() { }

  ngOnInit() {

  }

  back() {
    this.pricelistBack.emit();
  }

  add() {
    this.addPricelist.emit();
  }

}
