export abstract class Korisnik {
    id: string;
    datumRegistracije: Date;

    korisnickoIme: string;
    sifra: string;
    email: string;
    adresa: string;
    telefon: string;

    constructor(email: string, telefon: string) {
        this.email = email;
        this.telefon = telefon;
    }

}
