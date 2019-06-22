import { API_RESERVATION } from './../../globals';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) { }

  getReport(id: number): Observable<any> { 

    const headers = new HttpHeaders();

    return this.http.get(API_RESERVATION + 'reservation/report/' + id, {headers, responseType: 'text'});

  }

}
