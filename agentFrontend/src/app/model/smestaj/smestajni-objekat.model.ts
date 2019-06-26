import { Agent } from './../korisnik/agent.model';
import { SmestajnaJedinica } from './smestajna-jedinica.model';
import { KategorijaSmestaja } from './kategorija-smestaja.model';
import { Lokacija } from './lokacija.model';
import { TipSmestaja } from './tip-smestaja.model';
import { DodatnaUsluga } from './dodatna-usluga.model';
import { Otkazivanje } from './otkazivanje.model';
import { Cenovnik } from './cenovnik.model';
import { DisplayImageWrapper } from '../display-image-wrapper';

export class SmestajniObjekat {
    id: number;
    tipSmestaja: TipSmestaja;
    lokacija: Lokacija;
     opis: string;   //

     cenovnici: Cenovnik[];
     podrazumevaniCenovnik: Cenovnik;

     kategorijaSm: KategorijaSmestaja;
   // zvezdice: number;       // ??????????????/

    smestajnaJedinica: SmestajnaJedinica[];
    dodatnaUsluga: DodatnaUsluga[];

  //  agent: number;

     // FileSet???
     // slike: File[];
     slike: DisplayImageWrapper[];

    naziv: string;

    // constructor(id: number, naziv: string,  tip: TipSmestaja, kategorija: KategorijaSmestaja) {
    //   this.id = id;
    //   this.naziv = naziv;
    //   this.tipSmestaja = tip;
    //   this.kategorijaSm = kategorija;
    // }

    constructor(id: number, naziv: string,  tip: TipSmestaja, kategorija: KategorijaSmestaja, opis: string, podrazumevaniCenovnik: Cenovnik
      , cenovnici: Cenovnik[], dodatneUsluge: DodatnaUsluga[], slike: DisplayImageWrapper[]) {
      this.id = id;
      this.naziv = naziv;
      this.tipSmestaja = tip;
      this.kategorijaSm = kategorija;

      this.opis = opis;
      this.podrazumevaniCenovnik = podrazumevaniCenovnik;
      this.cenovnici = cenovnici;
      this.dodatnaUsluga = dodatneUsluge;
      this.slike = slike;
    }
}
