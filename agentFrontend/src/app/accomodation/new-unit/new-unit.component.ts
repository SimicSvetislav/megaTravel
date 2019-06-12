import { SmestajniObjekat } from './../../model/smestaj/smestajni-objekat.model';
import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { AccomodationService } from './../../service/accomodation.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookingService } from 'src/app/service/booking.service';

@Component({
  selector: 'app-new-unit',
  templateUrl: './new-unit.component.html',
  styleUrls: ['./new-unit.component.css']
})
export class NewUnitComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private accomodationService: AccomodationService) { }

  activeTab: any;
  newUnit: SmestajnaJedinica;
  object: SmestajniObjekat;

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      const objectId = params.get('objectId');
      this.accomodationService.getObject();
    });
    this.object = new SmestajniObjekat();

    this.newUnit = new SmestajnaJedinica();
    this.activeTab = 'basic-info';
  }

  addBasicInfo() {
    this.activeTab = 'facilities';
  }

  addImages() {

  }

  backImages() {
    this.activeTab = 'pricelist';

  }

  addFacilities() {
    this.activeTab = 'pricelist';
  }

  backFacilities() {
    this.activeTab = 'basic-info';

  }

  addPricelist() {
    this.activeTab = 'images';
  }

  backPricelist() {
    this.activeTab = 'facilities';

  }

}
