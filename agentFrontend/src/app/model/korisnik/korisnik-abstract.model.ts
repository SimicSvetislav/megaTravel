export abstract class Korisnik {
    id: string;
    datumRegistracije: Date;
    korisnickoIme: string;
    sifra: string;
    ime: string;
    prezime: string;
    email: string;
    adresa: string;
    telefon: string;
    uloga: string;
    stanje: string;

    constructor(ime: string, prezime: string, email: string, telefon: string) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
    }

}
