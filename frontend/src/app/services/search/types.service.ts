import { TYPES_API } from './../../globals';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TypesService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { } 

  getAll(): Observable<any> {
    return this.http.get(TYPES_API);
  }

  getOne(id: Number): Observable<any> {
    return this.http.get(TYPES_API + id);
  }

  update(extra: Object): Observable<any> {
    return this.http.put(TYPES_API, extra);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(TYPES_API + id);
  }

  post(extra: any): Observable<any> {
    return this.http.post(TYPES_API, extra); 
  }
}
