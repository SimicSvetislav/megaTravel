
import { SearchObject } from './../searchObject';
import { Slike } from './../slike';
import { SmestajniObjekat } from './../smestajniObjekat';
import { Component, OnInit } from '@angular/core';
import { EventBrokerService } from '../services/event-broker/event-broker.service';
import { Router } from '@angular/router';
import { ObjectService } from '../services/object/object.service';
//import { lookup } from 'dns';
import { NgbModal, NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { TokenStorageService } from '../services/auth/token-storage.service';
import { SearchService } from '../services/search/search.service';
import { asElementData } from '@angular/core/src/view';
import { ExtrasService } from '../services/search/extras.service';
import { ResultDTO } from '../resultDTO';

@Component({
  selector: 'app-preview',
  templateUrl: './preview.component.html',
  styleUrls: ['./preview.component.scss']
})
export class PreviewComponent implements OnInit {

  fromDate: NgbDate;
  toDate: NgbDate;
  hoveredDate: NgbDate;
  po: SearchObject = new SearchObject();
  pr: SearchObject = new SearchObject();
  objects: SmestajniObjekat[] = [];
  pictures: Slike[] = [];
  logged: Boolean = false;

  results: ResultDTO[] = [];

  userId: number;

  extras: Array<any>;

  dropdownSettings = {};
  selectedItems = [];

  constructor(private eventBroker: EventBrokerService, private token: TokenStorageService,
              private router: Router, private searchService: SearchService,
              private soService: ObjectService, private extrasService: ExtrasService,
              private modalService: NgbModal) { }

  ngOnInit() {

    this.userId = +this.token.getUser();

    //alert(this.userId)

    var stored = this.token.getSearch();
    if (stored) {
      this.po = JSON.parse(stored);
      this.searchService.search(this.po, this.userId).subscribe( data => { 
        this.objects = data;
      }, error => console.log(error));
    }

    this.extrasService.getAll().subscribe(data => {
      this.extras = data;
    });

    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'ime',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 5,
      allowSearchFilter: true
    };

    if(this.token.getUser() != null) {
      this.logged = true;
    }

    this.soService.getAll().subscribe(data => {
      this.objects = data;

      for(let o of this.objects) {
      //  alert("sadfklj: " + o.slike[0].putanja)
      
        
      }

    })
  }

  search() {
    
    this.pr.dolazak = new Date(this.fromDate.year, this.fromDate.month-1, this.fromDate.day);
    this.pr.odlazak = new Date(this.toDate.year, this.toDate.month-1, this.toDate.day);
    this.pr.dodatneUsluge = this.selectedItems.map(item => item.id);

    this.searchService.search(this.pr, this.userId).subscribe( data => { 
      this.results = data;
      this.objects = data;
    }, error => console.log(error));

  }

  onItemSelect(item: any) {
    this.selectedItems.push(item);
  }

  onSelectAll(items: any) {
    this.selectedItems = items;
  }

  onItemDeselect(item: any) {
    let ind = this.selectedItems.indexOf(item);
    this.selectedItems.splice(ind, 1);
    //alert(this.selectedItems);
  }

  onDeselectAll(items: any) {
    this.selectedItems = [];
  }

  onDateSelection(date: NgbDate) {
    if (!this.fromDate && !this.toDate) {
      this.fromDate = date;
    } else if (this.fromDate && !this.toDate && date.after(this.fromDate)) {
      this.toDate = date;
    } else {
      this.toDate = null;
      this.fromDate = date;
    }
  }

   

  isHovered(date: NgbDate) {
    return this.fromDate && !this.toDate && this.hoveredDate && date.after(this.fromDate) && date.before(this.hoveredDate);
  }

  isInside(date: NgbDate) {
    return date.after(this.fromDate) && date.before(this.toDate);
  }

  isRange(date: NgbDate) {
    return date.equals(this.fromDate) || date.equals(this.toDate) || this.isInside(date) || this.isHovered(date);
  }

  redirect(id) {
    this.router.navigate(['preview/' +id]);
  }
  openModal(content) {
    this.modalService.open(content, {backdropClass: 'light-blue-backdrop'});
  }

}
