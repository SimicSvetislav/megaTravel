import { AGENT_API, MAIN_API } from './../../globals';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AgentService {

  constructor(private http: HttpClient) { }


  getByReservation(id: number): Observable<any> {
    return this.http.get(MAIN_API + 'resAgent/' + id);
  }

}
