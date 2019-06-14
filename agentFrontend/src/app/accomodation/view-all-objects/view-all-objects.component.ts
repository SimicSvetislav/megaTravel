import { SmestajniObjekat } from 'src/app/model/smestaj/smestajni-objekat.model';
import { Component, OnInit } from '@angular/core';
import { AccomodationService } from 'src/app/service/accomodation.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-all-objects',
  templateUrl: './view-all-objects.component.html',
  styleUrls: ['./view-all-objects.component.css']
})
export class ViewAllObjectsComponent implements OnInit {

  objektiAgenta: SmestajniObjekat[] = [];

  constructor(private accomodationService: AccomodationService, private router: Router) { }

  ngOnInit() {
    this.accomodationService.getObjects().subscribe( data => {

    });

    this.objektiAgenta = [{id: 1, tipSmestaja: {id: 1, naziv: 'Bed and breakfast'},
    kategorija: {id: 1, naziv: '5 zvezdica'}, lokacija:  {id: 1, naziv: 'naziv', geoDuzina: {stepeni: 23, strana: 'W'},
     geoSirina: {stepeni: 45, strana: 'N'}}, naziv: 'Talija'},
     {id: 1, tipSmestaja: {id: 1, naziv: 'Bed and breakfast'},
    kategorija: {id: 1, naziv: '5 zvezdica'}, lokacija:  {id: 1, naziv: 'naziv', geoDuzina: {stepeni: 23, strana: 'W'},
     geoSirina: {stepeni: 45, strana: 'N'}}, naziv: 'Talija'},
     {id: 1, tipSmestaja: {id: 1, naziv: 'Bed and breakfast'},
    kategorija: {id: 1, naziv: '5 zvezdica'}, lokacija:  {id: 1, naziv: 'naziv', geoDuzina: {stepeni: 23, strana: 'W'},
     geoSirina: {stepeni: 45, strana: 'N'}}, naziv: 'Talija'},
     {id: 1, tipSmestaja: {id: 1, naziv: 'Bed and breakfast'},
    kategorija: {id: 1, naziv: '5 zvezdica'}, lokacija:  {id: 1, naziv: 'naziv', geoDuzina: {stepeni: 23, strana: 'W'},
     geoSirina: {stepeni: 45, strana: 'N'}}, naziv: 'Talija'},
     {id: 1, tipSmestaja: {id: 1, naziv: 'Bed and breakfast'},
    kategorija: {id: 1, naziv: '5 zvezdica'}, lokacija:  {id: 1, naziv: 'naziv', geoDuzina: {stepeni: 23, strana: 'W'},
     geoSirina: {stepeni: 45, strana: 'N'}}, naziv: 'Talija'},
     {id: 1, tipSmestaja: {id: 1, naziv: 'Bed and breakfast'},
    kategorija: {id: 1, naziv: '5 zvezdica'}, lokacija:  {id: 1, naziv: 'naziv', geoDuzina: {stepeni: 23, strana: 'W'},
     geoSirina: {stepeni: 45, strana: 'N'}}, naziv: 'Talija'},
      ];
  }

  detail(obj: SmestajniObjekat) {
    this.router.navigate(['object', obj.id]);
  }

}
