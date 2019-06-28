import { KrajnjiKorisnik } from './../korisnik/korisnik-krajnji.model';
import { Komentar } from './komentar.model';
import { Rezervacija } from './rezervacija.model';
import { Korisnik } from '../korisnik/korisnik-abstract.model';
import { SmestajnaJedinica } from '../smestaj/smestajna-jedinica.model';

export class RezervacijaKorisnika extends Rezervacija {
    datumRezervacije: string;

    cenaSmestaja: number;
    stanje: string;
    korisnik: number;

    ocenjeno: boolean;

    dodatneUsluge: number[];

    smestajnaJedinicaDTO: SmestajnaJedinica;

    constructor(id: number, pocetak: string, kraj: string, smestaj: number, popust: number, datumRezervacija: string,
        cena: number, stanje: string, k: number) {
        super(id, pocetak, kraj, smestaj, popust);
        this.datumRezervacije = datumRezervacija;
        this.cenaSmestaja = cena;
        this.stanje = stanje;
        this.korisnik = k;
    }
}

