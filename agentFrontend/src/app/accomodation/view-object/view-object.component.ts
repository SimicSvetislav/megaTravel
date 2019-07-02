import { SmestajniObjekat } from './../../model/smestaj/smestajni-objekat.model';
import { AccomodationService } from 'src/app/service/accomodation.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { RatingService } from 'src/app/service/rating/rating.service';

@Component({
  selector: 'app-view-object',
  templateUrl: './view-object.component.html',
  styleUrls: ['./view-object.component.css']
})
export class ViewObjectComponent implements OnInit {

  activeTab: any;
  objekat: SmestajniObjekat;

  averageMark: number;

  constructor(private router: Router, private accomodationService: AccomodationService, private activatedRoute: ActivatedRoute,
    private ratingService: RatingService) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(data => {
      const id: string = data.get('objectId');

      this.accomodationService.getObject(id).subscribe( object => {
        this.objekat = object;

        console.log(id);
        const units: string = data.get('units');
        if (units) {
          this.activeTab = 'units';
        } else {
          this.activeTab = 'basic-info';
        }

        }, ( error: Response) => {
          console.log(error);
      });



    });

    // this.objekat = {id: 1, tipSmestaja: {id: 1, naziv: 'Bed and breakfast'},
    // kategorija: {id: 1, naziv: '5 zvezdica'}, lokacija:  { naziv: 'naziv', geoDuzina: {stepeni: 23, strana: 'W'},
    //  geoSirina: {stepeni: 45, strana: 'N'}}, naziv: 'Talija'};

  }

  back() {
    this.router.navigate(['objects']);
  }

  averageGradeObj() {
    this.activeTab = 'average';
    // this.objekat.id = 13;
    //this.ratingService.averageGradeObject(1).subscribe(data => {
      this.ratingService.averageGradeObject(this.objekat.id).subscribe(data => {
      this.averageMark = data;
    }); 
  }

}
