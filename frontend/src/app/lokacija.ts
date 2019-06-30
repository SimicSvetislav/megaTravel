export class Lokacija {
    id: number;
    naziv: string;
    koordinate: Polozaj;
}

export class Polozaj {
    geoSirina: number;
    geoDuzina: number;
}