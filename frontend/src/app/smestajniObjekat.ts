import { Slike } from './slike';
import { Lokacija } from "./lokacija";
import { SmestajnaJedinica } from "./smestajnaJedinica";

export class SmestajniObjekat {
    id: number;
    kategorija: string;
    lokacija: Lokacija;
    naziv: string;
    opis: string;
    kategorijaSm: KategorijaSm;
    tipSmestaja: TipSmestaja;
    slike: Array<Slike>;
    dodatnaUsluga: Array<DodatnaUsluga>;
    smestajnaJedinica: Array<SmestajnaJedinica>;
    agent: number;
    podrazumevaniCenovnik: Cenovnik;

}

export class KategorijaSm {
    zvezdice: number;
    id: number;
}

export class TipSmestaja {
    naziv: Object;
    id: number;
}



export class DodatnaUsluga {
    id:number;
    jedinicaPlacanja: string;
    ime: string;
    cena: number;

}

export class Cenovnik {
    pocetak: string;
    kraj: string;
    cena: number;
    smestaj: number;
}