import { TokenStorageService } from './../services/auth/token-storage.service';
import { Slike } from './../slike';
import { SmestajniObjekat } from './../smestajniObjekat';
import { Component, OnInit } from '@angular/core';
import { EventBrokerService } from '../services/event-broker/event-broker.service';
import { Router } from '@angular/router';
import { ObjectService } from '../services/object/object.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { lookup } from 'dns';

@Component({
  selector: 'app-preview',
  templateUrl: './preview.component.html',
  styleUrls: ['./preview.component.scss']
})
export class PreviewComponent implements OnInit {

  objects: SmestajniObjekat[] = [];
  pictures: Slike[] = [];
  logged: Boolean = false;

  constructor(private eventBroker: EventBrokerService, 
              private router: Router,
              private soService: ObjectService,
              private modalService: NgbModal,
              private tokenStorage: TokenStorageService) { }

  ngOnInit() {


    this.soService.getAll().subscribe(data => {
      this.objects = data;

      for(let o of this.objects) {
      //  alert("sadfklj: " + o.slike[0].putanja)
      
        
      }

    })

    if(this.tokenStorage.getUser() != null) {
      this.logged = true;
    }



  }

  redirect(id) {
    this.router.navigate(['preview/' +id]);
  }
  openModal(content) {
    this.modalService.open(content, {backdropClass: 'light-blue-backdrop'});
  }

}
