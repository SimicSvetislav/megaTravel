import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MessagesService {

  CHAT_API = 'http://localhost:7070/';

  constructor(private http: HttpClient) { }

  getByReservation(id: number): Observable<any> {
    return this.http.get(this.CHAT_API + 'messages/res/' + id);
  }

}
