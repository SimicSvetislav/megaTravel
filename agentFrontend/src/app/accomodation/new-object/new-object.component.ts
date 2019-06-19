import { KategorijaSmestaja } from './../../model/smestaj/kategorija-smestaja.model';
import { TipSmestaja } from './../../model/smestaj/tip-smestaja.model';
import { AccomodationService } from './../../service/accomodation.service';
import { Component, OnInit } from '@angular/core';
import { SmestajniObjekat } from 'src/app/model/smestaj/smestajni-objekat.model';
import { Router } from '@angular/router';
import { Cenovnik } from 'src/app/model/smestaj/cenovnik.model';

@Component({
  selector: 'app-new-object',
  templateUrl: './new-object.component.html',
  styleUrls: ['./new-object.component.css']
})
export class NewObjectComponent implements OnInit {

  activeTab: any;

  newObject: SmestajniObjekat;

  constructor(private accomodationService: AccomodationService, private router: Router) { }

  ngOnInit() {
   this.newObject = new SmestajniObjekat(null, '', new TipSmestaja(1, 'hotel'), new KategorijaSmestaja(1, 4),
    '', undefined, [], [], []);

    this.activeTab = 'basic-info';
  }

  addNewObject() {
    if (this.newObject.naziv) {   // popunjen je basic info tab
      this.accomodationService.addObject(this.newObject).subscribe(data => {

      }, (error: Response) => {

      });
    }
  }

  addBasicInfo() {
    console.log(this.newObject);
    this.activeTab = 'facilities';
    // this.addNewObject();
  }

  backBasicInfo() {
    this.router.navigate([]);
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

  addImages() {
    this.addNewObject();
  }

  backImages () {
    this.activeTab = 'pricelist';
  }

}
