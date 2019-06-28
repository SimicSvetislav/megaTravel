import { TokenStorageService } from 'src/app/service/auth/toke-storage.service';
import { ToastrService } from 'ngx-toastr';
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

  constructor(private accomodationService: AccomodationService, private router: Router, private toastrService: ToastrService,
     private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    // if (!this.tokenStorage.isLogged()) {
    //   this.router.navigate(['login']);
    // }


   this.newObject = new SmestajniObjekat(null, '', new TipSmestaja(1, 'hotel'), new KategorijaSmestaja(1, 4),
    '', undefined, [], [], []);

    this.activeTab = 'basic-info';
  }

  addNewObject() {
    if (this.newObject.naziv) {   // popunjen je basic info tab
      if (this.newObject.slike.length === 0) {
        this.toastrService.warning('Morate dodati barem jednu sliku objekta!');
        return;
      }

      this.accomodationService.addObject(this.newObject).subscribe(data => {
        this.toastrService.success('Objekat uspesno dodat');
        this.router.navigate(['home']);
      }, (error: Response) => {
        if (error.status === 401) {
          this.toastrService.error('Nije potvrdjena autentifikacija');
        } else if (error.status === 409) {
          this.toastrService.error('Greska pri dobavljanju relevantnih podataka');
        } else {
          this.toastrService.error('Greska na serveru');
        }
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
