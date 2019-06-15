import { SmestajniObjekat } from './../../model/smestaj/smestajni-objekat.model';
import { AccomodationService } from 'src/app/service/accomodation.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-view-object',
  templateUrl: './view-object.component.html',
  styleUrls: ['./view-object.component.css']
})
export class ViewObjectComponent implements OnInit {

  activeTab: any;
  objekat: SmestajniObjekat;

  constructor(private router: Router, private accomodationService: AccomodationService, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(data => {
      const id: string = data.get('objectId');
      console.log(id);
      const units: string = data.get('units');
      if (units) {
        this.activeTab = 'units';
      } else {
        this.activeTab = 'basic-info';
      }

    });
    // this.accomodationService.getObject().subscribe( data => {

    // });
    this.objekat = {id: 1, tipSmestaja: {id: 1, naziv: 'Bed and breakfast'},
    kategorija: {id: 1, naziv: '5 zvezdica'}, lokacija:  {id: 1, naziv: 'naziv', geoDuzina: {stepeni: 23, strana: 'W'},
     geoSirina: {stepeni: 45, strana: 'N'}}, naziv: 'Talija'};

  }

  back() {
    this.router.navigate(['objects']);
  }

}
