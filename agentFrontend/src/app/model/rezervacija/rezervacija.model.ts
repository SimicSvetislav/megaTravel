import { SmestajnaJedinica } from '../smestaj/smestajna-jedinica.model';
export abstract class Rezervacija {
    id: number;
    datumPocetka: Date;
    datumZavrsetka: Date;

    smestaj: SmestajnaJedinica;
    popust: number;
}
