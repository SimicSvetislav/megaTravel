import { SmestajnaJedinica } from '../smestaj/smestajna-jedinica.model';
export abstract class Rezervacija {
    id: number;
    datumPocetka: Date;
    datumZavrsetka: Date;

    smestaj: SmestajnaJedinica;
    popust: number;

    constructor(id: number, pocetak: Date, kraj: Date, smestaj: SmestajnaJedinica, popust: number) {
        this.id = id;
        this.datumPocetka = pocetak;
        this.datumZavrsetka = kraj;
        this.smestaj = smestaj;
        this.popust = popust;
    }
}
