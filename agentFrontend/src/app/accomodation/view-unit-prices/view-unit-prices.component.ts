import { Cenovnik } from 'src/app/model/smestaj/cenovnik.model';
import { Component, OnInit, Input } from '@angular/core';
import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { SmestajniObjekat } from 'src/app/model/smestaj/smestajni-objekat.model';

@Component({
  selector: 'app-view-unit-prices',
  templateUrl: './view-unit-prices.component.html',
  styleUrls: ['./view-unit-prices.component.css']
})
export class ViewUnitPricesComponent implements OnInit {

  @Input()
  unit: SmestajnaJedinica;

  @Input()
  object: SmestajniObjekat;

  cenovnici: Cenovnik[];
  podrazumevaniCenovnik: Cenovnik;

  constructor() { }

  ngOnInit() {
    // this.cenovnici = [
    //     {pocetak: new Date(), kraj: new Date(), cena : 155, smestaj: undefined,
    //     pocetakStr: '2019-06-13', krajStr: '2019-15-08'},
    //     {pocetak: new Date(), kraj: new Date(), cena : 155, smestaj: undefined,
    //     pocetakStr: '2019-06-13', krajStr: '2019-15-08'},
    //     {pocetak: new Date(), kraj: new Date(), cena : 155, smestaj: undefined,
    //     pocetakStr: '2019-06-13', krajStr: '2019-15-08'},
    //     {pocetak: new Date(), kraj: new Date(), cena : 155, smestaj: undefined,
    //     pocetakStr: '2019-06-13', krajStr: '2019-15-08'},
    // ];
    // this.podrazumevaniCenovnik = this.cenovnici[0];

    this.cenovnici = this.object.cenovnici;
    this.podrazumevaniCenovnik = this.object.podrazumevaniCenovnik;
  }

}
