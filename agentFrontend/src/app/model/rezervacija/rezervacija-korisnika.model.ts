import { Komentar } from './komentar.model';
import { Rezervacija } from './rezervacija.model';
import { Korisnik } from '../korisnik/korisnik-abstract.model';

export class RezervacijaKorisnika extends Rezervacija {
    datumRezervacije: Date;

    cenaSmestaja: number;
    stanje: string;
    korisnik: Korisnik;
    ocena: number;
    komentar: Komentar;

    // ???????????????
    obradjeno: boolean;
    procenatOtkazivanje: number;
}

