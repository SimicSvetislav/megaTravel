import { SmestajniObjekat } from '../smestaj/smestajni-objekat.model';
import { Korisnik } from './korisnik-abstract.model';
import { Rezervacija } from '../rezervacija/rezervacija.model';

export class Agent extends Korisnik {
    poslovniMaticniBroj: number;

    smestajniObjekti: SmestajniObjekat[];
    rezervacije: Rezervacija[];

    // ??????????????????????
    filteriPoruka: string[];
}
