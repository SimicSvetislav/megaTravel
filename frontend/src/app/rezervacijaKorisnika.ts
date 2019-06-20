import { Komentar } from './komentar';
import { SmestajnaJedinica } from './smestajnaJedinica';
import { User } from './user';
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
}