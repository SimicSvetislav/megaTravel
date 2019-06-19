import { Agent } from './../types';
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

  post(agent: Agent): Observable<any> {
    return this.http.post(AGENT_API, agent);
  }

  put(agent: Agent): Observable<any> {
    return this.http.put(AGENT_API, agent);
  }

  remove(id: number): Observable<any> {
    return this.http.delete(AGENT_API + id);
  }

}
