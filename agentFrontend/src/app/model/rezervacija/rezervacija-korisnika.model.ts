import { KrajnjiKorisnik } from './../korisnik/korisnik-krajnji.model';
import { Komentar } from './komentar.model';
import { Rezervacija } from './rezervacija.model';
import { Korisnik } from '../korisnik/korisnik-abstract.model';
import { SmestajnaJedinica } from '../smestaj/smestajna-jedinica.model';

export class RezervacijaKorisnika extends Rezervacija {
    datumRezervacije: Date;

    cenaSmestaja: number;
    stanje: string;
    korisnik: KrajnjiKorisnik;


    // ??????????????????
    ocena: number;
    komentar: Komentar;

    // ???????????????
    obradjeno: boolean;
    procenatOtkazivanje: number;

    constructor(id: number, pocetak: Date, kraj: Date, smestaj: SmestajnaJedinica, popust: number, datumRezervacija: Date,
        cena: number, stanje: string, k: KrajnjiKorisnik) {
        super(id, pocetak, kraj, smestaj, popust);
        this.datumRezervacije = datumRezervacija;
        this.cenaSmestaja = cena;
        this.stanje = stanje;
        this.korisnik = k;
    }
}

