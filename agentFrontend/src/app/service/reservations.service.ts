import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationsService {

  RESERVATIONS_API = 'http://localhost:8762/reservations/';

  constructor(private http: HttpClient) { }

  getUserByReservation(id: number): Observable<any> {
    return this.http.get(this.RESERVATIONS_API + 'userByRes/' + id);
  }
}
