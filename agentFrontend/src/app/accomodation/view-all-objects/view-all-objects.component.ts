import { SmestajniObjekat } from 'src/app/model/smestaj/smestajni-objekat.model';
import { Component, OnInit } from '@angular/core';
import { AccomodationService } from 'src/app/service/accomodation.service';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/service/auth/toke-storage.service';

@Component({
  selector: 'app-view-all-objects',
  templateUrl: './view-all-objects.component.html',
  styleUrls: ['./view-all-objects.component.css']
})
export class ViewAllObjectsComponent implements OnInit {

  objektiAgenta: SmestajniObjekat[] = [];

  constructor(private accomodationService: AccomodationService, private router: Router, private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    // if (!this.tokenStorage.isLogged()) {
    //   this.router.navigate(['login']);
    // }

    this.accomodationService.getObjects().subscribe( data => {
        this.objektiAgenta = data;
    }, ( error: Response) => {
      console.log(error);
    });
  }

  detail(obj: SmestajniObjekat) {
    this.router.navigate(['object', obj.id]);
  }

}
