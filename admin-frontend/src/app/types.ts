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

export class Rating {
    room = 333;
    object = 33;
    comment = '33333333333333';
    grade = 5;
    user = 1;
    approved = false;
}