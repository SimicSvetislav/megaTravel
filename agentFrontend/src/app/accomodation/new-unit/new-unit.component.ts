import { SmestajniObjekat } from './../../model/smestaj/smestajni-objekat.model';
import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { AccomodationService } from './../../service/accomodation.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingService } from 'src/app/service/booking.service';
import { TipSmestaja } from 'src/app/model/smestaj/tip-smestaja.model';
import { KategorijaSmestaja } from 'src/app/model/smestaj/kategorija-smestaja.model';
import { Cenovnik } from 'src/app/model/smestaj/cenovnik.model';

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
      this.accomodationService.getObject();
    });
    this.object = new SmestajniObjekat(1, 'Talija', new TipSmestaja(1, 'hotel'), new KategorijaSmestaja(1, '4*'));

    this.newUnit = new SmestajnaJedinica(1, 2, true, new Cenovnik(), []);
    this.activeTab = 'basic-info';
  }

  addBasicInfo() {
    this.activeTab = 'facilities';
  }

  // zavrsna operacija
  addImages() {
    const url: string = 'object/' + this.object.id + '/units';
    this.router.navigate([url]);
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
