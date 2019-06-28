import { Korisnik } from './korisnik-abstract.model';

export class KrajnjiKorisnik extends Korisnik {
    // kategorija: string;
    // ponudjeniPopustiNakonOtkazivanja: boolean;
    // lokacija: Lokacija;

    ime: string;
    prezime: string;

    // rezervacije: RezervacijaKorisnika[];

    constructor(ime: string, prezime: string, email: string, telefon: string) {
        super(email, telefon);

        this.ime = ime;
        this.prezime = prezime;
    }
}
