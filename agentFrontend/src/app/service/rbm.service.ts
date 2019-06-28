import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { stringify } from '@angular/core/src/util';

@Injectable({
  providedIn: 'root'
})
export class RbmService {

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

  constructor(private http: HttpClient) { }
}
