import { SmestajniObjekat } from './../../model/smestaj/smestajni-objekat.model';
import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { AccomodationService } from './../../service/accomodation.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingService } from 'src/app/service/booking.service';
import { TipSmestaja } from 'src/app/model/smestaj/tip-smestaja.model';
import { KategorijaSmestaja } from 'src/app/model/smestaj/kategorija-smestaja.model';
import { Cenovnik } from 'src/app/model/smestaj/cenovnik.model';
import { Otkazivanje } from 'src/app/model/smestaj/otkazivanje.model';

@Component({
  selector: 'app-new-unit',
  templateUrl: './new-unit.component.html',
  styleUrls: ['./new-unit.component.css']
})
export class NewUnitComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private accomodationService: AccomodationService,
    private router: Router) { }

  activeTab: any;
  newUnit: SmestajnaJedinica;
  object: SmestajniObjekat;

  ngOnInit() {

    this.activatedRoute.paramMap.subscribe(params => {
      const objectId = params.get('objectId');
      this.accomodationService.getObject(objectId).subscribe(data => {
        this.object = data;
        this.newUnit = new SmestajnaJedinica(null, -1, false, this.object.id, new Otkazivanje());

      });
    });

    this.activeTab = 'basic-info';
  }

  addBasicInfo() {
    // this.activeTab = 'facilities';
    this.accomodationService.addUnit(this.object.id.toString(), this.newUnit).subscribe(data => {
      const url: string = this.backToUnitsUrl();
      this.router.navigate([url]);
    }, (error: Response) => {

    });
  }

  backBasicInfo() {
    const url: string = this.backToUnitsUrl();
    this.router.navigate([url]);
  }

  backToUnitsUrl() {
    return 'object/' + this.object.id + '/units';
  }

  // zavrsna operacija
  // addImages() {
  //   const url: string = 'object/' + this.object.id + '/units';
  //   this.router.navigate([url]);
  // }

  // backImages() {
  //   this.activeTab = 'pricelist';

  // }

  // addFacilities() {
  //   this.activeTab = 'pricelist';
  // }

  // backFacilities() {
  //   this.activeTab = 'basic-info';

  // }

  // addPricelist() {
  //   this.activeTab = 'images';
  // }

  // backPricelist() {
  //   this.activeTab = 'facilities';

  // }

}
