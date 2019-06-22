import { Pipe, PipeTransform } from '@angular/core';
import { RezervacijaKorisnika } from '../model/rezervacija/rezervacija-korisnika.model';

@Pipe({
    name: 'bookUser'
  })
  export class UserBookPipe implements PipeTransform {
    transform(value: RezervacijaKorisnika) {
      if (value) {
        // const firstName = value.korisnik.ime;
        // const lastName = value.korisnik.prezime;
        // const email = value.korisnik.email;
        // const telephone = value.korisnik.telefon;

        return value.korisnik;
        // return firstName + ' ' + lastName + ', mail:' + email + ', telefon:' + telephone;
      } else {
        return;
      }
    }
}
