import { TokenStorageService } from './../services/auth/token-storage.service';
import { SmestajniObjekat, Cenovnik } from './../smestajniObjekat';
import { Route, Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ObjectService } from '../services/object/object.service';

@Component({
  selector: 'app-preview-object',
  templateUrl: './preview-object.component.html',
  styleUrls: ['./preview-object.component.scss']
})
export class PreviewObjectComponent implements OnInit {


  objectId;
  objekat: SmestajniObjekat = new SmestajniObjekat();
  showNavigationIndicators = true;
  podrazumevaniCenovnik: Cenovnik = new Cenovnik();
  logged: Boolean = false;
  id;
  existPricelist: Boolean = false;

  constructor(private router: Router,
    private route: ActivatedRoute,
    private soService: ObjectService,
    private tokenStorage: TokenStorageService) { }

  ngOnInit() {

    this.objectId = this.route.snapshot.params['id'];

    if(this.tokenStorage.getUser() != null) {
      this.logged = true;
    }

    this.soService.getOneById(this.objectId).subscribe(data => {
      this.objekat = data;

      this.podrazumevaniCenovnik = this.objekat.podrazumevaniCenovnik;
      
      if(this.podrazumevaniCenovnik == null) {
        this.existPricelist = false;
      } else {
        this.existPricelist = true;
      }
    })

  }

  rezervisi() {

  }

}
 