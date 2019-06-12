import { Cena } from './cena.model';
import { SmestajnaJedinica } from './smestajna-jedinica.model';
export class Cenovnik {
    pocetak: Date;
    kraj: Date;

    cena: Cena;
    smestaj: SmestajnaJedinica;
}
