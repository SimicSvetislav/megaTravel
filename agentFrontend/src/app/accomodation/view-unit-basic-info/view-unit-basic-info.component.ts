import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-view-unit-basic-info',
  templateUrl: './view-unit-basic-info.component.html',
  styleUrls: ['./view-unit-basic-info.component.css']
})
export class ViewUnitBasicInfoComponent implements OnInit {

  @Input()
  unit: SmestajnaJedinica;

  constructor() { }

  ngOnInit() {
  }

}
