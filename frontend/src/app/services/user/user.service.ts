import { API_USER, API_RESERVATION } from './../../globals';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private RESERVATION_API = '//localhost:8122/';
  private USER_API = '//localhost:8152/';

  constructor(private http: HttpClient) { }

  getAllReservationsById(id): Observable<any> {
    return this.http.get(API_RESERVATION + 'user/' + id);
  }

  getLogged(id): Observable<any> {
    return this.http.get(API_USER + 'user/' + id);
  }

  


}
