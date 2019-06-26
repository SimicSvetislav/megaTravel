import { ToastrService } from 'ngx-toastr';
import { DisplayImageWrapper } from './../../model/display-image-wrapper';
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { SmestajniObjekat } from 'src/app/model/smestaj/smestajni-objekat.model';

@Component({
  selector: 'app-choose-images',
  templateUrl: './choose-images.component.html',
  styleUrls: ['./choose-images.component.css']
})
export class ChooseImagesComponent implements OnInit {

  @Input()
  unit: SmestajnaJedinica;

  @Input()
  object: SmestajniObjekat;

  @Output()
  backImages = new EventEmitter;

  @Output()
  addImages = new EventEmitter;

  imagePreview: string | ArrayBuffer;
  url: string;

  selectedImages: DisplayImageWrapper[] = [];
  // reader: FileReader = new FileReader();

  constructor(private toastrService: ToastrService) { }

  ngOnInit() {
    this.url = '../../../assets/hotel-icon.png';

    this.selectedImages = this.object.slike;
  }

  add() {
    this.object.slike = this.selectedImages;
    this.addImages.emit();
  }

  back() {
    this.backImages.emit();
  }


  onFileUpload(event) {
    // let  file: File;
    // const imageWrapper: DisplayImageWrapper = new DisplayImageWrapper();
    for (const f of event.target.files) {
      let  file: File;

      // provera tipa na ulazu
      const type: string = f.type;
      const contains: boolean = type.includes('image');
      if (!contains) {
        this.toastrService.warning('Dozvoljen je unos samo slike');
        return;
      }
      const imageWrapper: DisplayImageWrapper = new DisplayImageWrapper();

      file = f;
      const reader = new FileReader();

      // ucitavanje fajla preko readera, pregled fajla -> filter formata
      reader.onload = () => {

        imageWrapper.putanja = file.name;
        imageWrapper.value = reader.result;
        this.selectedImages.push(imageWrapper);
      };
      // ovo ispod trigerije ovo iznad
      reader.readAsDataURL(file);   // rezultat na kraju je 64bitni enkodirana slika
    }
  }

  deleteImage(imageWrapper: DisplayImageWrapper) {
    const index = this.selectedImages.indexOf(imageWrapper);
    this.selectedImages.splice(index, 1);
  }

}
