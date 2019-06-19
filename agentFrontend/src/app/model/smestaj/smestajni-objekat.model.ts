import { Agent } from './../korisnik/agent.model';
import { SmestajnaJedinica } from './smestajna-jedinica.model';
import { KategorijaSmestaja } from './kategorija-smestaja.model';
import { Lokacija } from './lokacija.model';
import { TipSmestaja } from './tip-smestaja.model';
import { Rejting } from './rejting.model';
import { DodatnaUsluga } from './dodatna-usluga.model';
import { Otkazivanje } from './otkazivanje.model';
import { Cenovnik } from './cenovnik.model';

export class SmestajniObjekat {
    id: number;
    tipSmestaja: TipSmestaja;
    lokacija: Lokacija;
    // opis: string;   //

    // cenovnici: Cenovnik[];
    // podrazumevaniCenovnik: Cenovnik;

    kategorija: KategorijaSmestaja;
   // zvezdice: number;       // ??????????????/

    // PRIVREMENO...
    // rejting: Rejting;

    smestajnaJedinica: SmestajnaJedinica[];
    dodatnaUsluga: DodatnaUsluga[];

  //  agent: Agent;

  // FileSet??? digli na objekat
    // slike: File[];

    naziv: string;

    constructor(id: number, naziv: string,  tip: TipSmestaja, kategorija: KategorijaSmestaja) {
      this.id = id;
      this.naziv = naziv;
      this.tipSmestaja = tip;
      this.kategorija = kategorija;
    }
}
