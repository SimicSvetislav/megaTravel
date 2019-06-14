import { Cena } from './cena.model';
import { SmestajnaJedinica } from './smestajna-jedinica.model';
export class Cenovnik {
    pocetak: Date;
    kraj: Date;

    pocetakStr: string;
    krajStr: string;

    cena: Cena;
    smestaj: SmestajnaJedinica;

}
