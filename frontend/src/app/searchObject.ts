import { TipSmestaja, KategorijaSm, DodatnaUsluga } from './smestajniObjekat';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap';
export class SearchObject {
    lokacija: string;
    dolazak: Date;
    odlazak: Date;
    brojOsoba: number;
    tipSmestaja: string;
    kategorijaSmestaja: number;
    udaljenost: number;
    besplatnoOktazivanje: boolean = false;
    otkazivanjePre: number;
    dodatneUsluge = [];
}