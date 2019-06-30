import { API_RESERVATION, API_MAIN, API_USER } from './../../globals';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AgentService {

  constructor(private http: HttpClient) { }


  getByReservation(id: number): Observable<any> {
    return this.http.get(API_RESERVATION + 'resAgent/' + id);
  }

  getOne(id: number): Observable<any> {
    return this.http.get(API_USER + 'agent/' + id);
  }

  getOneByObject(id: number): Observable<any> {
    return this.http.get(API_USER + 'agent/byObject/' + id);
  }

}
