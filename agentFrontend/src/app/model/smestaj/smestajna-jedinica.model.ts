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

    opis: string;
    dodatneUsluge: DodatnaUsluga[];
    otkazivanje: Otkazivanje;

    // ??????????????????????
    povecanjeVeciBrojOsoba: number;
    sObjekat: SmestajniObjekat;
    komentari: Komentar[];
    rejting: Rejting;

    // FileSet???
    slike: File[];
}


