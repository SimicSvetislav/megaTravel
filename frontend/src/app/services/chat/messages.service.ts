import { Observable } from 'rxjs/Observable';
import { MESSAGES } from './../../globals';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MessagesService {

  constructor(private http: HttpClient) { }

  getByReservation(id: number): Observable<any> {
    return this.http.get(MESSAGES + 'res/' + id);
  }

}
