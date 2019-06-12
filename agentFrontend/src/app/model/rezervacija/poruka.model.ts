import { Korisnik } from '../korisnik/korisnik-abstract.model';
import { RezervacijaKorisnika } from './rezervacija-korisnika.model';

export interface Poruka {
    timestamp: Date;
    id: number;

    posiljalac: Korisnik;
    primalac: Korisnik;
    tekst: string;
    sadrzaj: any;

    rezervacija: RezervacijaKorisnika;

}
