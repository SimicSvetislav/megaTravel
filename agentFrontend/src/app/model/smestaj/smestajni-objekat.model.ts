import { Agent } from './../korisnik/agent.model';
import { SmestajnaJedinica } from './smestajna-jedinica.model';
import { KategorijaSmestaja } from './kategorija-smestaja.model';
import { Lokacija } from './lokacija.model';
import { TipSmestaja } from './tip-smestaja.model';
import { Rejting } from './rejting.model';
import { DodatnaUsluga } from './dodatna-usluga.model';
import { Otkazivanje } from './otkazivanje.model';

export class SmestajniObjekat {
    id: number;
    tipSmestaja: TipSmestaja;
    lokacija: Lokacija;
    otkazivanje: Otkazivanje;
    kategorija: KategorijaSmestaja;
    zvezdice: number;       // ??????????????/


    rejting: Rejting;

    smestajneJedinice: SmestajnaJedinica[];
    dodatneUsluge: DodatnaUsluga[];

    agent: Agent;

    // ????????????????????
    naziv: string;
}
