import { Lokacija } from './lokacija';
import { SmestajnaJedinica } from './smestajnaJedinica';
import { Slike } from './slike';
export class ResultDTO {
    jedinica: SmestajnaJedinica;
    slike: Array<Slike>;
    ocena: number;
    kategorija: number;
    opis: string;
    cena: number;
    nazivObj: string;
    lokacija: Lokacija;
}