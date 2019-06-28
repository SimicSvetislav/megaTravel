export class SignUpInfo {
    firstName: string;
    email: string;
    role: string[];
    password: string;
    lastName: string;
    address: string;
    phoneNumber: string;
    lokacija: string;
    geoSirina: number;
    geoDuzina: number;

    constructor(firstName: string, email: string, password: string,
                lastName:string, address:string, phoneNumber:string,
                lokacija: string, geoSirina: number, geoDuzina: number) {
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.lokacija = lokacija;
        this.geoDuzina = geoDuzina;
        this.geoSirina = geoSirina;
    }
}