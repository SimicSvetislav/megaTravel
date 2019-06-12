import { AccomodationService } from './../../service/accomodation.service';
import { Component, OnInit } from '@angular/core';
import { SmestajniObjekat } from 'src/app/model/smestaj/smestajni-objekat.model';

@Component({
  selector: 'app-new-object',
  templateUrl: './new-object.component.html',
  styleUrls: ['./new-object.component.css']
})
export class NewObjectComponent implements OnInit {

  activeTab: any;

  newObject: SmestajniObjekat;

  constructor(private accomodationService: AccomodationService) { }

  ngOnInit() {
    this.newObject = new SmestajniObjekat();

    this.activeTab = 'basic-info';
  }

  addNewObject() {
    if (this.newObject.naziv) {
      this.accomodationService.addObject().subscribe(data => {

      });
    }



  }

  addBasicInfo() {
    console.log(this.newObject);
    this.activeTab = 'facilities';
  }

  backFacilities() {
    this.activeTab = 'basic-info';
  }

}
