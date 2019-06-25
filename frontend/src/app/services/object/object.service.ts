import { API_MAIN } from './../../globals';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ObjectService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(API_MAIN + 'so/all');
  }

  getOneById(id): Observable<any> {
    return this.http.get(API_MAIN + 'so/' + id);
  }

  getUnitById(id): Observable<any> {
    return this.http.get(MAIN_API + 'sj/'+id);
  }

}
