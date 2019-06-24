import { SmestajniObjekat } from './../smestajniObjekat';
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

  constructor(private router: Router,
    private route: ActivatedRoute,
    private soService: ObjectService) { }

  ngOnInit() {

    this.objectId = this.route.snapshot.params['id'];

    this.soService.getOneById(this.objectId).subscribe(data => {
      this.objekat = data;

    })

  }

}
