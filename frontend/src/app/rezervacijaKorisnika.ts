import { Komentar } from './komentar';
import { SmestajnaJedinica } from './smestajnaJedinica';
import { User } from './user';
import { SmestajniObjekat } from './smestajniObjekat';
export class RezervacijaKorisnika {
    id: number;
    smestajnaJedinica: number;
    popust: number;
    datumRezervacije: string;
    datumPocetka: string;
    datumZavrsetka: string;
    cenaSmestaja: number;
    stanje: string;
    korisnik: number;
    ocenjeno: Boolean;
    smestajniObjekat: SmestajniObjekat;
}

export class RezervacijaKorisnika2 {
    id: number;
    popust: number;
    smestajE: number;
    datumRezervacije: string;
    datumPocetka: string;
    datumZavrsetka: string;
    cenaSmestaja: number;
    stanje: string;
    korisnikE: number;
}
