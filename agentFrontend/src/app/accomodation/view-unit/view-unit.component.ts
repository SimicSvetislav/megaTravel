import { ActivatedRoute, Router } from '@angular/router';
import { AccomodationService } from './../../service/accomodation.service';
import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { Component, OnInit } from '@angular/core';
import { SmestajniObjekat } from 'src/app/model/smestaj/smestajni-objekat.model';

@Component({
  selector: 'app-view-unit',
  templateUrl: './view-unit.component.html',
  styleUrls: ['./view-unit.component.css']
})
export class ViewUnitComponent implements OnInit {


  activeTab: any;
  object: SmestajniObjekat;
  unit: SmestajnaJedinica;

  constructor(private accomodationService: AccomodationService, private activatedRoute: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(data => {
      const id: string = data.get('objectId');
      console.log(id);
    });
    // this.accomodationService.getObject().subscribe( data => {

    // });
    this.object = {id: 1, tipSmestaja: {id: 1, naziv: 'Bed and breakfast'},
    kategorija: {id: 1, naziv: '5 zvezdica'}, lokacija:  {id: 1, naziv: 'naziv', geoDuzina: {stepeni: 23, strana: 'W'},
     geoSirina: {stepeni: 45, strana: 'N'}}, naziv: 'Talija'};


    this.unit = {id: 1, brojKreveta: 2, balkon: true, sObjekat: undefined,
      cenovnici: [{cena: {iznos: 155, valuta: 'RSD'}, pocetak: new Date(),
    pocetakStr: '2019-06-13', kraj: new Date(), krajStr: '2019-08-05', smestaj: undefined}], podrazumevaniCenovnik:
      {cena: {iznos: 155, valuta: 'RSD'}, pocetak: new Date(),
      pocetakStr: '2019-06-13', kraj: new Date(), krajStr: '2019-08-05', smestaj: undefined}};

    this.activeTab = 'basic-info';
  }

  back() {
    const url: string = 'object/' + this.object.id + '/units';
    this.router.navigate([url]);
  }

}
