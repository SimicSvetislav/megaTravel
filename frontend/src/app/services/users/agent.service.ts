import { AGENT_API, MAIN_API, API_RESERVATION } from './../../globals';
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

}
