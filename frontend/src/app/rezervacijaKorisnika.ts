import { SmestajnaJedinica } from './smestajnaJedinica';
import { User } from './user';
export class RezervacijaKorisnika {
    id: number;
    cenaSmestaja: number;
    stanje: string;
    korisnik: User;
    obradjeno: Boolean;
    datumRezervacije: Date;
    datumPocetka: Date;
    datumZavrsetka: Date;
    smestajnaJedinica: SmestajnaJedinica;
}