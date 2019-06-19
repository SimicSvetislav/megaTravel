import { Lokacija } from './../smestaj/lokacija.model';
import { Korisnik } from './korisnik-abstract.model';
import { RezervacijaKorisnika } from '../rezervacija/rezervacija-korisnika.model';

export class KrajnjiKorisnik extends Korisnik {
    kategorija: string;
    ponudjeniPopustiNakonOtkazivanja: boolean;
    lokacija: Lokacija;

    ime: string;
    prezime: string;

    rezervacije: RezervacijaKorisnika[];

    constructor(ime: string, prezime: string, email: string, telefon: string) {
        super(ime, prezime, email, telefon);
    }
}
