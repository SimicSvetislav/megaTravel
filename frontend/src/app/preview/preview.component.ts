import { DatePipe } from '@angular/common';
import { ResultDTO } from './../resultDTO';
import { TypesService } from './../services/search/types.service';
import { Otkazivanje } from './../otkazivanje';
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
import { ToastrService } from 'ngx-toastr';
import { CategoriesService } from '../services/search/categories.service';
import { RezervacijaKorisnika } from '../rezervacijaKorisnika';
import { ReservationService } from '../services/reservations/reservation.service';

@Component({
  selector: 'app-preview',
  templateUrl: './preview.component.html',
  styleUrls: ['./preview.component.scss']
})
export class PreviewComponent implements OnInit {

  fromDate: NgbDate;
  toDate: NgbDate;
  hoveredDate: NgbDate;
  prosli: SearchObject = new SearchObject();
  pr: SearchObject = new SearchObject();
  objects: SmestajniObjekat[] = [];
  //results = new Array();
  pictures: Slike[] = [];
  logged: Boolean = false;

  results: ResultDTO[] = [];

  userId: number;
  sorter: string;

  extras: Array<any>;
  types: Array<any>;
  categories: Array<any>;

  dropdownSettings = {};
  selectedItems = [];

  constructor(private eventBroker: EventBrokerService, private token: TokenStorageService,
              private router: Router, private searchService: SearchService,
              private soService: ObjectService, private extrasService: ExtrasService,
              private modalService: NgbModal, private toastr: ToastrService,
              private typesService: TypesService, private categoriesService: CategoriesService,
              private datePipe: DatePipe, private resService: ReservationService) { }

  ngOnInit() {

    this.userId = +this.token.getUser();

    //alert(this.userId)

    var stored = this.token.getSearch();
    if (stored) {
      this.prosli = JSON.parse(stored);
      this.searchService.search(this.prosli, this.userId).subscribe( data => { 
        this.results = data;
        //this.token.saveSearch(undefined);
      }, error => console.log(error));
    }

  /*  this.extrasService.getAll().subscribe(data => {
      this.extras = data;
    });*/

  /*  this.typesService.getAll().subscribe(data => {
      this.types = data;
    });*/

  /*  this.categoriesService.getAll().subscribe(data => {
      this.categories = data;
    });*/

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

  sort() {
    
    if (this.sorter==="Reccomendation") {
      // Pozivati rule based module

    } else if (this.sorter==="Price") {
      // Moze na frontu
      this.results.sort((a,b) => a.cena < b.cena ? -1 : a.ocena > b.ocena ? 1 : 0);

    } else if (this.sorter==="Distance") {
      // ?
      
    } else if (this.sorter==="Rating") {
      // Moze na frontu
      this.results.sort((a,b) => a.ocena < b.ocena ? 1 : a.ocena > b.ocena ? -1 : 0);
      
    } else if (this.sorter==="Category") {
      // Moze na frontu
      this.results.sort((a,b) => a.kategorija < b.kategorija ? 1 : a.kategorija > b.kategorija ? -1 : 0);
      
    }

  }

  search() {

    if (!this.pr.lokacija) {
      this.toastr.warning("Please enter location");
      return;
    }

    if (!this.pr.brojOsoba) {
      this.toastr.warning("Please enter number of persons");
      return;
    }

    if (!Number.isInteger(this.pr.brojOsoba) || this.pr.brojOsoba <= 0) {
      this.toastr.warning("Number of persons must be whole positive number");
      return;
    }

    if (!this.fromDate || !this.toDate) {
      this.toastr.warning("Please select begin and end date");
      return;
    }

    if (this.pr.udaljenost !== undefined) {
      if (this.pr.udaljenost <= 0) { 
        this.toastr.warning("Distance must be whole number");
      }
    }

    if (this.pr.besplatnoOktazivanje === true) {
      if (this.pr.otkazivanjePre !== undefined) {
        if (!Number.isInteger(this.pr.otkazivanjePre) || this.pr.otkazivanjePre <= 0) {
          this.toastr.warning("Number of days for cancellation must be whole positive number");
          return;
        }
      }
    }
    
    this.pr.dolazak = new Date(this.fromDate.year, this.fromDate.month-1, this.fromDate.day);
    this.pr.odlazak = new Date(this.toDate.year, this.toDate.month-1, this.toDate.day);
    this.pr.dodatneUsluge = this.selectedItems.map(item => item.id);

    this.searchService.search(this.pr, this.userId).subscribe( data => { 
      this.results = data;
      this.token.saveSearch(this.pr);
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

  rezervacija: RezervacijaKorisnika = new RezervacijaKorisnika();
  pom: string;

  rezervisi(idJedinice,nazivObjekta,dolazak,odlazak,cena) {

    this.rezervacija.smestajnaJedinica = idJedinice;
    this.rezervacija.datumRezervacije =  this.datePipe.transform(new Date(), 'yyyy-MM-dd');
    this.rezervacija.datumPocetka = dolazak;
    this.rezervacija.datumZavrsetka = odlazak;
    this.rezervacija.cenaSmestaja = cena;
    this.rezervacija.stanje = "AKTIVNA";
    
    this.pom = this.token.getUser();
    
    this.rezervacija.korisnik = parseInt(this.pom);
    this.rezervacija.ocenjeno = false;

    this.resService.makeReservation(this.rezervacija).subscribe(data => {
      //alert("Ne znam zasto ne radi toster");
      this.toastr.show("Uspesno ste rezervisali!");
      this.router.navigate(['/profile/' + this.pom])
    })


  }

}
