import { DisplayImageWrapper } from './../../model/display-image-wrapper';
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

  images: string[];
  selectedImages: DisplayImageWrapper[];

  constructor() { }

  ngOnInit() {
    // this.images = ['../../../assets/hotel-icon.png', '../../../assets/Capture.PNG', '../../../assets/ct.jpg'];
    // this.images = new Array<string>();
    // for ( const w of this.object.slike) {
    //   this.images.push(w.value.toString());
    // }

    this.selectedImages = new Array();
    for ( const w of this.object.slike) {
      this.selectedImages.push(w);
    }
  }

}
