import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private RESERVATION_API = '//localhost:8122/';

  constructor(private http: HttpClient) { }

  getAllReservationsById(id): Observable<any> {
    return this.http.get(this.RESERVATION_API + 'user/' + id);
  }


}
