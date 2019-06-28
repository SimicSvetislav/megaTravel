import { RezervacijaKorisnika2 } from './../../rezervacijaKorisnika';
import { Message, Poruka } from './../../message';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RezervacijaKorisnika } from 'src/app/rezervacijaKorisnika';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RbmService {
  
  
  viseOsoba(rezervacija: RezervacijaKorisnika2, id: number) {
    return this.http.post(this.RBM_API + "more/" + id, rezervacija, { responseType: 'text' });
  }
  
  filter(arg0: string, msg: Object): Observable<string> {
    return this.http.put(this.RBM_API + "filter/" + arg0, msg, { responseType: 'text' });
  }
  
  reccomend(id: number) {
    return this.http.get(this.RBM_API + "rec/" + id, { responseType: 'text' });
  }

  checkDiscount(rez: RezervacijaKorisnika2, id:number) {
    return this.http.post(this.RBM_API + "make/" + id, rez, { responseType: 'text' });
  }

  constructor(private http: HttpClient) { }

  RBM_API = 'http://localhost:8020/'

  forbid(word: string, id: string) {
    return this.http.post(this.RBM_API + "addRuleF/" + id, {word}, { responseType: 'text' });
  }
  submit(persons: number, percent: number, id: string) {
    return this.http.post(this.RBM_API + "addRuleP1/" + id + "/" + persons, percent, { responseType: 'text' });
  }
  add(discount: number, res: number, id: string) {
    return this.http.post(this.RBM_API + "addRuleP2/" + id + "/" + res, discount, { responseType: 'text' });
  }

  checkMsg(poruka: Poruka, id: string) {
    return this.http.post(this.RBM_API + "filter/" + id, poruka, { responseType: 'text' });
  }

}
