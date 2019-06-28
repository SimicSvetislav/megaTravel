import { SmestajnaJedinica } from '../smestaj/smestajna-jedinica.model';
export abstract class Rezervacija {
    id: number;
    datumPocetka: string;
    datumZavrsetka: string;

    smestajnaJedinica: number;
    popust: number;

    constructor(id: number, pocetak: string, kraj: string, smestaj: number, popust: number) {
        this.id = id;
        this.datumPocetka = pocetak;
        this.datumZavrsetka = kraj;
        this.smestajnaJedinica = smestaj;
        this.popust = popust;
    }
}
