import { AccomodationService } from 'src/app/service/accomodation.service';
import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { SmestajniObjekat } from 'src/app/model/smestaj/smestajni-objekat.model';
import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-all-units',
  templateUrl: './view-all-units.component.html',
  styleUrls: ['./view-all-units.component.css']
})
export class ViewAllUnitsComponent implements OnInit {

  @Input()
  object: SmestajniObjekat;

  units: SmestajnaJedinica[];

  constructor(private accomodation: AccomodationService, private router: Router) { }

  ngOnInit() {
    this.units = [{id: 1, brojKreveta: 2, balkon: true,  sObjekat: undefined, cenovnici: [], podrazumevaniCenovnik:
      {cena: {iznos: 155, valuta: 'RSD'}, pocetak: new Date(),
      pocetakStr: '2019-06-13', kraj: new Date(), krajStr: '2019-08-05', smestaj: undefined}},
      {id: 1, brojKreveta: 2, balkon: false,  sObjekat: undefined, cenovnici: [], podrazumevaniCenovnik:
        {cena: {iznos: 155.03, valuta: 'RSD'}, pocetak: new Date(),
        pocetakStr: '2019-06-13', kraj: new Date(), krajStr: '2019-08-05', smestaj: undefined}},
        {id: 1, brojKreveta: 2, balkon: false, sObjekat: undefined, cenovnici: [], podrazumevaniCenovnik:
          {cena: {iznos: 155, valuta: 'RSD'},  pocetak: new Date(),
          pocetakStr: '2019-06-13', kraj: new Date(), krajStr: '2019-08-05', smestaj: undefined}},
    ];
  }

  detail(unit: SmestajnaJedinica) {
    const url: string = 'object/' + this.object.id + '/unit/' + unit.id;
    this.router.navigate([url]);
  }

  newUnit() {
    const url: string = 'object/' + this.object.id + '/newUnit';
    this.router.navigate([url]);
  }

}
