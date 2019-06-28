
export interface Poruka {
    timestamp: Date;
    id: number;

    sender: number;
    receiver: number;
    text: string;
    payload: any;

    reservation: number;

}
