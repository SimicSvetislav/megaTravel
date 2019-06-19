import { AccomodationService } from 'src/app/service/accomodation.service';
import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { SmestajniObjekat } from 'src/app/model/smestaj/smestajni-objekat.model';
import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-all-units',
  templateUrl: './view-all-units.component.html',
  styleUrls: ['./view-all-units.component.css']
})
export class ViewAllUnitsComponent implements OnInit {

  @Input()
  object: SmestajniObjekat;

  units: SmestajnaJedinica[];

  constructor(private accomodationService: AccomodationService, private router: Router) { }

  ngOnInit() {
    this.units = this.object.smestajnaJedinica;
    // this.accomodationService.getUnits( this.object.id.toString() ).subscribe(data => {
    //   this.units = data;
    // });
  }

  detail(unit: SmestajnaJedinica) {
    const url: string = 'object/' + this.object.id + '/unit/' + unit.id;
    this.router.navigate([url]);
  }

  newUnit() {
    const url: string = 'object/' + this.object.id + '/newUnit';
    this.router.navigate([url]);
  }

}
