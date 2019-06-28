export class Message {
    timestamp: Date;
    sender: number;
    receiver: number;
    text: string;
    payload: any;
    reservation: number;
}

export class Poruka {
    timestamp: Date;
    posiljalac: number;
    primalac: number;
    tekst: string;
    objekat: any;
    reservation: number;
}