import { ToastrModule, ToastrService } from 'ngx-toastr';
import { BookingService } from 'src/app/service/booking.service';
import { KrajnjiKorisnik } from './../../model/korisnik/korisnik-krajnji.model';
import { Cenovnik } from './../../model/smestaj/cenovnik.model';
import { KategorijaSmestaja } from './../../model/smestaj/kategorija-smestaja.model';
import { SmestajniObjekat } from 'src/app/model/smestaj/smestajni-objekat.model';
// import { Component, OnInit, PipeTransform } from '@angular/core';
// import { Country } from 'src/app/model/country.model';
// import { Observable } from 'rxjs';
// import { FormControl } from '@angular/forms';
// import { DecimalPipe } from '@angular/common';
// import { map, startWith } from 'rxjs/operators';


// @Component({
//   selector: 'app-view-all-bookings',
//   templateUrl: './view-all-bookings.component.html',
//   styleUrls: ['./view-all-bookings.component.css'],
//   providers: [DecimalPipe]

// })
// export class ViewAllBookingsComponent implements OnInit {

//   activeTab: any;

//    COUNTRIES: Country[] = [
//     {
//       name: 'Russia',
//       flag: 'f/f3/Flag_of_Russia.svg',
//       area: 17075200,
//       population: 146989754
//     },
//     {
//       name: 'Canada',
//       flag: 'c/cf/Flag_of_Canada.svg',
//       area: 9976140,
//       population: 36624199
//     },
//     {
//       name: 'United States',
//       flag: 'a/a4/Flag_of_the_United_States.svg',
//       area: 9629091,
//       population: 324459463
//     },
//     {
//       name: 'China',
//       flag: 'f/fa/Flag_of_the_People%27s_Republic_of_China.svg',
//       area: 9596960,
//       population: 1409517397
//     }
//   ];

//   countries$: Observable<Country[]>;
//   filter = new FormControl('');

//   objects;
//   selectedReservation: Country;

//    search(text: string, pipe: PipeTransform): Country[] {
//     return this.COUNTRIES.filter(country => {
//       const term = text.toLowerCase();
//       return country.name.toLowerCase().includes(term)
//           || pipe.transform(country.area).includes(term)
//           || pipe.transform(country.population).includes(term);
//     });
//   }


//   constructor(pipe: DecimalPipe) {
//     this.countries$ = this.filter.valueChanges.pipe( startWith(''),
//       map(text => this.search(text, pipe))
//     );
//   }

//   ngOnInit() {
//     this.activeTab = 'upcoming';

//     this.objects = ['Canada', 'Russia'];
//   }

//   back() {

//   }

//   selectedRowChanged(selectedRow: Country) {
//     this.selectedReservation = selectedRow;
//   }



// }
import { Component, OnInit, PipeTransform } from '@angular/core';
import { Observable } from 'rxjs';
import { FormControl } from '@angular/forms';
import { DecimalPipe } from '@angular/common';
import { map, startWith } from 'rxjs/operators';
import { Rezervacija } from 'src/app/model/rezervacija/rezervacija.model';
import { RezervacijaKorisnika } from 'src/app/model/rezervacija/rezervacija-korisnika.model';
import { TipSmestaja } from 'src/app/model/smestaj/tip-smestaja.model';
import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { Korisnik } from 'src/app/model/korisnik/korisnik-abstract.model';
import { Otkazivanje } from 'src/app/model/smestaj/otkazivanje.model';
import { TokenStorageService } from 'src/app/service/auth/toke-storage.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-view-all-bookings',
  templateUrl: './view-all-bookings.component.html',
  styleUrls: ['./view-all-bookings.component.css'],
  providers: [DecimalPipe]

})
export class ViewAllBookingsComponent implements OnInit {

  activeTab: any;


  allBookings: RezervacijaKorisnika[] = [];
  allBookingsByActiveTab: RezervacijaKorisnika[] = [];

  filteredBookings: Observable<RezervacijaKorisnika[]>;
  filter = new FormControl('');

  selectedReservation: Rezervacija;


  //  search(obj: SmestajniObjekat): RezervacijaKorisnika[] {
  //   return this.allBookings.filter(booking => {
  //     if (obj) {
  //       const term = obj.naziv.toLowerCase();
  //       const id = obj.id;
  //       return booking.smestaj.sObjekat === id;
  //       // return booking.smestaj.sObjekat.naziv.toLowerCase().includes(term);
  //     } else {
  //       return booking;
  //     }

  //   });
  // }


  constructor(private bookingService: BookingService, private tokenStorage: TokenStorageService, private router: Router,
    private toastrService: ToastrService) {

  }

  ngOnInit() {

    if (!this.tokenStorage.isLogged()) {
      this.router.navigate(['login']);
    }

    this.activeTab = 'upcoming';


    this.bookingService.getAllBookings().subscribe(data => {
      this.allBookings = data;
      console.log('pre sortiranja');
      console.log(data);
      console.log('posle sortiranja');
      data.sort((book2, book1) => book2.id - book1.id);
      console.log(data);

      // if (this.activeTab === 'upcoming') {
      //   this.allBookingsByActiveTab = this.allBookings.filter(book => book.stanje === 'REZERVISANO');
      // } else {
      //   this.allBookingsByActiveTab = this.allBookings.filter(book => book.stanje === 'ODRADJENO');
      // }

      this.filterList();

      this.filteredBookings = data; // temp
      // this.filteredBookings = this.filter.valueChanges.pipe( startWith(undefined),
      //   map(text => this.search(text))
      // );
    });


  }

  changeSectedTAb(changeTo: string) {
    this.activeTab = changeTo;
    this.selectedReservation = undefined;

    this.filterList();
  }

  filterList() {
    switch (this.activeTab) {
      case 'upcoming' : {
          this.allBookingsByActiveTab = this.allBookings.filter(book => book.stanje === 'REZERVISANO');
          break;
        }
      case 'confirmed' : {
          this.allBookingsByActiveTab = this.allBookings.filter(book => book.stanje === 'POTVRDJENO');
          break;
        }
      case 'history' : {
          this.allBookingsByActiveTab = this.allBookings.filter(book => book.stanje === 'REALIZOVANO');
          break;
          }
      case 'cancelled' : {
          this.allBookingsByActiveTab = this.allBookings.filter(book => book.stanje === 'OTKAZANO');
          break;
        }
      default : {
        this.toastrService.error('Greska');
      }

    }
  }

  back() {
    this.router.navigate(['home']);
  }

  selectedRowChanged(selectedRow: RezervacijaKorisnika) {
    this.selectedReservation = selectedRow;
  }

  confirmBook() {
    this.bookingService.confirmBooking(this.selectedReservation.id.toString()).subscribe(data => {
      this.selectedReservation = undefined;
      this.ngOnInit();
    }, (error: Response) => {

    });
  }

  // genData(): RezervacijaKorisnika[] {
  //   const n: RezervacijaKorisnika[] = [];
  //   let rez;
  //   let smestaj: SmestajnaJedinica;
  //   for (let i = 0; i < 6; ++i) {
  //     smestaj = {id: i, brojKreveta: i + 2, otkazivanje : new Otkazivanje(), balkon: true, sObjekat : i, opis: '', oznaka: ''};

  //     if (i % 3 === 0) {
  //       smestaj.sObjekat = 1;
  //     } else {
  //       smestaj.sObjekat = 1;
  //     }


  //     rez = new RezervacijaKorisnika(i, '2019-05-01', '2019-05-01', 1, 0.0, '2019-05-01', 400, 'rezervisano',
  //      1);
  //     n.push(rez);
  //   }

  //   return n;
  // }

}
