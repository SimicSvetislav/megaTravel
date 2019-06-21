import { Korisnik } from '../korisnik/korisnik-abstract.model';
import { RezervacijaKorisnika } from './rezervacija-korisnika.model';

export interface Poruka {
    timestamp: Date;
    id: number;

    sender: number;
    receiver: number;
    text: string;
    payload: any;

    reservation: number;

}
