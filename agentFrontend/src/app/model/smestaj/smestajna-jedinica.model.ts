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

    // ova dva digli na ojekat
    // cenovnici: Cenovnik[];
    // podrazumevaniCenovnik: Cenovnik;

    otkazivanje: Otkazivanje;

    // ??????????????????????
    // komentari: Komentar[];

     // id objekta
     sObjekat: number;

    // constructor() { }

    constructor(id: number, kreveta: number, balkon: boolean, sObjekat: number, otkazivanje: Otkazivanje) {
        this.id = id;
        this.brojKreveta = kreveta;
        this.balkon = balkon;
        this.sObjekat = sObjekat;
        this.otkazivanje = otkazivanje;
    }
}


