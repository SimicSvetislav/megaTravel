export class Extra {
    id: number;
    ime: string;
    cena: number;
}

export class Type {
    id: number;
    naziv: string;
}

export class CategorySm {
    id: number;
    zvezdice: number;
}

export class Agent {
    id: number;
    datumRegistracije: Date;
    korisnickoIme: string;
    sifra: string;
    email: string;
    adresa = '';
    telefon = '';
    poslovniMaticniBroj: string;
    ime = '';
    prezime = '';
}

export class Message {
    timestamp: Date;
    sender: number;
    receiver: number;
    text: string;
    payload: any;
    reservation: number;
}

export class Admin {
    id: number;
    datumRegistracije: Date;
    korisnickoIme: string;
    sifra: string;
    email: string;
    adresa = '';
    telefon = '';
    ime = '';
    prezime = '';
}