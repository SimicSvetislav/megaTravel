import { Component, OnInit, Input } from '@angular/core';
import { SmestajniObjekat } from 'src/app/model/smestaj/smestajni-objekat.model';

@Component({
  selector: 'app-view-object-images',
  templateUrl: './view-object-images.component.html',
  styleUrls: ['./view-object-images.component.css']
})
export class ViewObjectImagesComponent implements OnInit {

  @Input()
  object: SmestajniObjekat;

  images;

  constructor() { }

  ngOnInit() {
    this.images = ['../../../assets/hotel-icon.png', '../../../assets/Capture.PNG', '../../../assets/ct.jpg'];
  }

}
