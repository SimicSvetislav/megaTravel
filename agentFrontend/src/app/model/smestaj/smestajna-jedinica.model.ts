import { Komentar } from './../rezervacija/komentar.model';
import { DodatnaUsluga } from './dodatna-usluga.model';
import { Cenovnik } from './cenovnik.model';
import { Otkazivanje } from './otkazivanje.model';
import { Rejting } from './rejting.model';
import { SmestajniObjekat } from './smestajni-objekat.model';

export class SmestajnaJedinica {
    id: number;
    brojKreveta: number;
    balkon: boolean;

    cenovnici: Cenovnik[];
    podrazumevaniCenovnik: Cenovnik;

    // privremeno zakomentarisano
    // opis: string;   // ?????? digli na objekat
   // dodatneUsluge: DodatnaUsluga[];
   // otkazivanje: Otkazivanje;

    // ??????????????????????
    // povecanjeVeciBrojOsoba: number;
     sObjekat: SmestajniObjekat;
    // komentari: Komentar[];
    // rejting: Rejting;

    // FileSet??? digli na objekat
    // slike: File[];

    constructor(id: number, kreveta: number, balkon: boolean, podrazumevani: Cenovnik, cenovnici: Cenovnik[]) {
        this.id = id;
        this.brojKreveta = kreveta;
        this.balkon = balkon;
        this.podrazumevaniCenovnik = podrazumevani;
        this.cenovnici = cenovnici;
       // this.sObjekat = objekat;
    }
}


