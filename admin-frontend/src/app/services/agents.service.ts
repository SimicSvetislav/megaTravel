import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AGENT_API } from '../globals';

@Injectable({
  providedIn: 'root'
})
export class AgentsService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) {

  }

  getAgents(): Observable<any> {
    return this.http.get(AGENT_API);
  }

  getOne(id: Number): Observable<any> {
    return this.http.get(AGENT_API + id);
  }

}
