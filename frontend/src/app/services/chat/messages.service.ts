import { Observable } from 'rxjs/Observable';
import { CHAT_API } from './../../globals';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MessagesService {

  constructor(private http: HttpClient) { }

  getByReservation(id: number): Observable<any> {
    return this.http.get(CHAT_API + 'messages/res/' + id);
  }

}
