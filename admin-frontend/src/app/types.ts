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
    adresa: string;
    telefon = '';
    poslovniMaticniBroj: string;
    ime: string;
    prezime: string;
}