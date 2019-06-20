import { Komentar } from './komentar';
import { SmestajnaJedinica } from './smestajnaJedinica';
import { User } from './user';
export class RezervacijaKorisnika {
    id: number;
    cenaSmestaja: number;
    stanje: string;
    korisnik: User;
    obradjeno: Boolean;
    datumRezervacije: string;
    datumPocetka: string;
    datumZavrsetka: string;
    smestaj: SmestajnaJedinica;
    komentar: Komentar;
    ocenjeno: Boolean;
}