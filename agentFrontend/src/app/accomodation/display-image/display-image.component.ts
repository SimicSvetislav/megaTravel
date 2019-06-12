import { DisplayImageWrapper } from './../../model/display-image-wrapper';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-display-image',
  templateUrl: './display-image.component.html',
  styleUrls: ['./display-image.component.css']
})
export class DisplayImageComponent implements OnInit {

  @Input()
  imageWrapper: DisplayImageWrapper;

  @Output()
  deleteImage = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  removeImage() {
    this.deleteImage.emit(this.imageWrapper);
  }

}
