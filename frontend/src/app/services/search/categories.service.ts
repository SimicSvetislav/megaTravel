import { CATEGORIES_API } from './../../globals';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> { 
    return this.http.get(CATEGORIES_API);
  }

  getOne(id: Number): Observable<any> {
    return this.http.get(CATEGORIES_API + id);
  }

  update(extra: Object): Observable<any> {
    return this.http.put(CATEGORIES_API, extra);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(CATEGORIES_API + id);
  }

  post(extra: any): Observable<any> {
    return this.http.post(CATEGORIES_API, extra);
  }
}
