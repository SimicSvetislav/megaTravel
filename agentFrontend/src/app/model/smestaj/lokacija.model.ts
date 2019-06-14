import { Koordinate } from './koordinate.model';
import { Rejting } from './rejting.model';
export class Lokacija {
    id: number;
    naziv: string;
   // opis: string;
    geoSirina: Koordinate;
    geoDuzina: Koordinate;
   // rejting: Rejting;
}
