import { Extra } from './../types';
import { Observable } from 'rxjs';
import { EXTRAS_API } from './../globals';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ExtrasService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(EXTRAS_API);
  }

  getOne(id: Number): Observable<any> {
    return this.http.get(EXTRAS_API + id);
  }

  update(extra: Object): Observable<any> {
    return this.http.put(EXTRAS_API, extra);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(EXTRAS_API + id);
  }

  post(extra: Extra): Observable<any> {
    return this.http.post(EXTRAS_API, extra);
  }

}
