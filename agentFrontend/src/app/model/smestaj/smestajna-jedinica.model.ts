import { Komentar } from './../rezervacija/komentar.model';
import { DodatnaUsluga } from './dodatna-usluga.model';
import { Cenovnik } from './cenovnik.model';
import { Otkazivanje } from './otkazivanje.model';
import { SmestajniObjekat } from './smestajni-objekat.model';

export class SmestajnaJedinica {
    id: number;

    opis: string;
    oznaka: string;

    brojKreveta: number;
    balkon: boolean;

    // ova dva digli na ojekat
    // cenovnici: Cenovnik[];
    // podrazumevaniCenovnik: Cenovnik;

    otkazivanje: Otkazivanje;


     // id objekta
     sObjekat: number;

    constructor(id: number, kreveta: number, balkon: boolean, sObjekat: number, otkazivanje: Otkazivanje) {
        this.id = id;
        this.brojKreveta = kreveta;
        this.balkon = balkon;
        this.sObjekat = sObjekat;
        this.otkazivanje = otkazivanje;
    }
}


