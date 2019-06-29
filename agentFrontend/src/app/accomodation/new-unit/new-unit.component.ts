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
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-new-unit',
  templateUrl: './new-unit.component.html',
  styleUrls: ['./new-unit.component.css']
})
export class NewUnitComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private accomodationService: AccomodationService,
    private router: Router, private toastrService: ToastrService) { }

  activeTab: any;
  newUnit: SmestajnaJedinica;
  object: SmestajniObjekat;

  ngOnInit() {

    this.activatedRoute.paramMap.subscribe(params => {
      const objectId = params.get('objectId');
      this.accomodationService.getObject(objectId).subscribe(data => {
        this.object = data;
        this.newUnit = new SmestajnaJedinica(null, -1, false, this.object.id, new Otkazivanje());
        this.newUnit.opis = '';
        this.newUnit.cenovnici = [];
        this.newUnit.podrazumevanaCena = 0;
      });
    });

    this.activeTab = 'basic-info';
  }

  addBasicInfo() {
     this.activeTab = 'pricelist';
  }

  backBasicInfo() {
    const url: string = this.backToUnitsUrl();
    this.router.navigate([url]);
  }

  backToUnitsUrl() {
    return 'object/' + this.object.id + '/units';
  }

  // zavrsna operacija
  addPricelist() {
    // this.activeTab = 'images';
    this.accomodationService.addUnit(this.object.id.toString(), this.newUnit).subscribe(data => {
      const url: string = this.backToUnitsUrl();
      this.toastrService.success('Soba uspesno dodata');
      this.router.navigate([url]);
    }, (error: Response) => {

    });
  }

  backPricelist() {
    this.activeTab = 'basic-info';

  }

}
