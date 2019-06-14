import { Component, OnInit, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { Poruka } from 'src/app/model/rezervacija/poruka.model';

@Component({
  selector: 'app-view-all-messages-table',
  templateUrl: './view-all-messages-table.component.html',
  styleUrls: ['./view-all-messages-table.component.css']
})
export class ViewAllMessagesTableComponent implements OnInit, OnChanges {


  @Input()
  messagesType: string;

  // @Output()
  // selectedMessageChanged: EventEmitter<Poruka> = new EventEmitter();

  @Output()
  selectedMessageChanged = new EventEmitter();

  poruke: Poruka[];

  selectedRows: boolean[];

  constructor() { }

  ngOnInit() {

    this.genData();
  }

  genData() {
    this.poruke = [ {id: 1, timestamp: new Date(), tekst: 'lorem ipsum lfadjlfjalfjaldfjalkkjlfadjlkf',
    sadrzaj: '', rezervacija: undefined, posiljalac: {id: '1', datumRegistracije: new Date(), ime: 'osoba1',
  prezime: 'osobnic1',  korisnickoIme: 'fla', sifra: 'flak', adresa: 'adresa', email: 'lukajvnv@gmail.com', telefon: '',
  uloga: '', stanje : ''},  primalac: undefined },
  {id: 1, timestamp: new Date(), tekst: 'lorem ipsum lfadjlfjalfjaldfjalkkjlfadjlkf',
    sadrzaj: '', rezervacija: undefined, posiljalac: {id: '1', datumRegistracije: new Date(), ime: 'osoba2',
  prezime: 'osobnic2',  korisnickoIme: 'fla', sifra: 'flak', adresa: 'adresa', email: 'marko@gmail.com', telefon: '',
  uloga: '', stanje : ''},  primalac: undefined },
  {id: 1, timestamp: new Date(), tekst: 'lorem ipsum lfadjlfjalfjaldfjalkkjlfadjlkf',
    sadrzaj: '', rezervacija: undefined, posiljalac: {id: '1', datumRegistracije: new Date(), ime: 'osoba3',
  prezime: 'osobnic3',  korisnickoIme: 'fla', sifra: 'flak', adresa: 'adresa', email: 'marinko@gmail.com', telefon: '',
  uloga: '', stanje : ''},  primalac: undefined },
  {id: 1, timestamp: new Date(), tekst: 'lorem ipsum lfadjlfjalfjaldfjalkkjlfadjlkf',
    sadrzaj: '', rezervacija: undefined, posiljalac: {id: '1', datumRegistracije: new Date(), ime: 'osoba3',
  prezime: 'osobnic3',  korisnickoIme: 'fla', sifra: 'flak', adresa: 'adresa', email: 'marinko@gmail.com', telefon: '',
  uloga: '', stanje : ''},  primalac: undefined },
  {id: 1, timestamp: new Date(), tekst: 'lorem ipsum lfadjlfjalfjaldfjalkkjlfadjlkf',
    sadrzaj: '', rezervacija: undefined, posiljalac: {id: '1', datumRegistracije: new Date(), ime: 'osoba3',
  prezime: 'osobnic3',  korisnickoIme: 'fla', sifra: 'flak', adresa: 'adresa', email: 'marinko@gmail.com', telefon: '',
  uloga: '', stanje : ''},  primalac: undefined },
  {id: 1, timestamp: new Date(), tekst: 'lorem ipsum lfadjlfjalfjaldfjalkkjlfadjlkf',
    sadrzaj: '', rezervacija: undefined, posiljalac: {id: '1', datumRegistracije: new Date(), ime: 'osoba3',
  prezime: 'osobnic3',  korisnickoIme: 'fla', sifra: 'flak', adresa: 'adresa', email: 'marinko@gmail.com', telefon: '',
  uloga: '', stanje : ''},  primalac: undefined },
  {id: 1, timestamp: new Date(), tekst: 'lorem ipsum lfadjlfjalfjaldfjalkkjlfadjlkf',
    sadrzaj: '', rezervacija: undefined, posiljalac: {id: '1', datumRegistracije: new Date(), ime: 'osoba3',
  prezime: 'osobnic3',  korisnickoIme: 'fla', sifra: 'flak', adresa: 'adresa', email: 'marinko@gmail.com', telefon: '',
  uloga: '', stanje : ''},  primalac: undefined },
  ];

    if ( this.messagesType === 'inbox') {
      this.poruke.push({id: 1, timestamp: new Date(), tekst: 'lorem ipsum lfadjlfjalfjaldfjalkkjlfadjlkf',
      sadrzaj: 'INBOX', rezervacija: undefined, posiljalac: {id: '1', datumRegistracije: new Date(), ime: 'osoba1',
    prezime: 'osobnic1',  korisnickoIme: 'fla', sifra: 'flak', adresa: 'adresa', email: 'inbox@gmail.com', telefon: '',
    uloga: '', stanje : ''},  primalac: undefined });
    } else if (this.messagesType === 'sent') {
      this.poruke.push({id: 1, timestamp: new Date(), tekst: 'lorem ipsum lfadjlfjalfjaldfjalkkjlfadjlkf',
      sadrzaj: 'SENT', rezervacija: undefined, posiljalac: {id: '1', datumRegistracije: new Date(), ime: 'osoba1',
    prezime: 'osobnic1',  korisnickoIme: 'fla', sifra: 'flak', adresa: 'adresa', email: 'sent@gmail.com', telefon: '',
    uloga: '', stanje : ''},  primalac: undefined });
    } else {
      console.log('error');
    }

    this.selectedRows = new Array(this.poruke.length);
    for (let i = 0; i < this.poruke.length; ++i) {
      this.selectedRows[i] = false;
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    // if (changes.) {

    // }
    console.log('promena');
    this.genData();
  }

  select (p: Poruka, i: number) {
    this.selectedRows = this.selectedRows.fill(false, 0, this.selectedRows.length);
    // this.selectedRows.map((item) =>  { item =  false; console.log(item); }); // ne radi posao
    this.selectedRows[i] = true;
    console.log('selektovao');
    this.selectedMessageChanged.emit(p);
  }

}
