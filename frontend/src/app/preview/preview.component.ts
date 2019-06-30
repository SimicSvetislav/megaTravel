import { UserService } from './../services/users/user.service';
import { User } from './../user';
import { Lokacija } from './../lokacija';
import { DatePipe } from '@angular/common';
import { RezervacijaKorisnika, RezervacijaKorisnika2 } from './../rezervacijaKorisnika';

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
import { ReservationService } from '../services/reservations/reservation.service';
import { RbmService } from '../services/rbm/rbm.service';

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
  viseOsoba: Boolean = false;

  results: ResultDTO[] = [];

  userId: number;
  sorter: string;

  extras: Array<any>;
  types: Array<any>;
  categories: Array<any>;

  dropdownSettings = {};
  selectedItems = [];

  user: User;

  constructor(private eventBroker: EventBrokerService, private token: TokenStorageService,
    private router: Router, private searchService: SearchService,
    private soService: ObjectService, private extrasService: ExtrasService,
    private modalService: NgbModal, private toastr: ToastrService,
    private typesService: TypesService, private categoriesService: CategoriesService,
    private datePipe: DatePipe, private resService: ReservationService,
    private userService: UserService) { }
  ngOnInit() {

    this.userId = +this.token.getUser();

    //alert(this.userId)

    if (this.userId) {
      this.userService.getLogged(this.userId).subscribe( data => {

      }, error => {
        console.log(error)
      })
    }

    var stored = this.token.getSearch();
    if (stored) {
      this.prosli = JSON.parse(stored);
      this.searchService.search(this.prosli, this.userId).subscribe(data => {
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

    if (this.token.getUser() != null) {
      this.logged = true;
    }

    this.soService.getAll().subscribe(data => {
      this.objects = data;

      for (let o of this.objects) {
        //  alert("sadfklj: " + o.slike[0].putanja)


      }

    })
  }

  sort() {

    if (this.sorter === "Reccomendation") {
      // Pozivati rule based module
      /*this.rbm.reccomend(this.userId).subscribe(data => {
        alert(data)
      }, error => console.log(error))*/

    } else if (this.sorter === "Price") {
      // Moze na frontu
      this.results.sort((a, b) => a.cena < b.cena ? -1 : a.ocena > b.ocena ? 1 : 0);

    } else if (this.sorter === "Distance") {
      // ?
      this.results.sort((a, b) => 
        this.distanceForLocations(a.lokacija, this.user.lokacija) <= 
        this.distanceForLocations(b.lokacija, this.user.lokacija) ? -1 : 1);

    } else if (this.sorter === "Rating") {
      // Moze na frontu
      this.results.sort((a, b) => a.ocena < b.ocena ? 1 : a.ocena > b.ocena ? -1 : 0);

    } else if (this.sorter === "Category") {
      // Moze na frontu
      this.results.sort((a, b) => a.kategorija < b.kategorija ? 1 : a.kategorija > b.kategorija ? -1 : 0);

    }

  }

  distanceForLocations(l1: Lokacija, l2: Lokacija) {

    return this.distance(l1.koordinate.geoSirina, l2.koordinate.geoSirina,
      l1.koordinate.geoDuzina, l2.koordinate.geoDuzina, 0.0, 0.0);

  }

  toRadians(degrees: number) {
    return degrees * Math.PI / 180;
  }

  distance(lat1: number, lat2: number, lon1: number, lon2: number, el1: number, el2: number) {

    const R = 6371;

    const latDistance = this.toRadians(lat2 - lat1);
    const lonDistance = this.toRadians(lon2 - lon1);
    const a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
      + Math.cos(this.toRadians(lat1)) * Math.cos(this.toRadians(lat2))
      * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
    let c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    let distance = R * c * 1000; // convert to meters

    let height = el1 - el2;

    distance = Math.pow(distance, 2) + Math.pow(height, 2);

    return Math.sqrt(distance);
  }

  search() {

    /*if (!this.pr.lokacija) {
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
    }*/

    this.pr.dolazak = new Date(this.fromDate.year, this.fromDate.month - 1, this.fromDate.day);
    this.pr.odlazak = new Date(this.toDate.year, this.toDate.month - 1, this.toDate.day);
    this.pr.dodatneUsluge = this.selectedItems.map(item => item.id);

    this.searchService.search(this.pr, this.userId).subscribe(data => {
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
    this.router.navigate(['preview/' + id]);
  }
  openModal(content) {
    this.modalService.open(content, { backdropClass: 'light-blue-backdrop' });
  }

  rezervacija: RezervacijaKorisnika = new RezervacijaKorisnika();
  pom: string;
  sessionSearch: string;
  sesObj;

  rezervisi(idJedinice, nazivObjekta, dolazak, odlazak, cena) {



    if (!dolazak || !odlazak) {
      this.toastr.warning("Datum ce se vuci iz sesije");
      this.sessionSearch = this.token.getSearch();
      this.sesObj = JSON.parse(this.sessionSearch);

      dolazak = this.sesObj.dolazak;
      odlazak = this.sesObj.odlazak;


    }

    this.rezervacija.smestajnaJedinica = idJedinice;
    this.rezervacija.datumRezervacije = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
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

  toProfile(obj: number) {
    this.router.navigate(['/preview/' + obj]);
  }

}
