import { SmestajniObjekat } from 'src/app/model/smestaj/smestajni-objekat.model';
import { Component, OnInit, Input } from '@angular/core';
import { NgbRatingConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-view-object-basic-info',
  templateUrl: './view-object-basic-info.component.html',
  styleUrls: ['./view-object-basic-info.component.css'],
  providers: [NgbRatingConfig] // add NgbRatingConfig to the component providers
})
export class ViewObjectBasicInfoComponent implements OnInit {

  @Input()
  object: SmestajniObjekat;


  constructor(config: NgbRatingConfig) {
    config.max = 5;
    config.readonly = true;
  }

  ngOnInit() {


  }

}
