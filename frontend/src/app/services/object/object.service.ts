import { MAIN_API } from './../../globals';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ObjectService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(MAIN_API + 'so/all');
  }

  getOneById(id): Observable<any> {
    return this.http.get(MAIN_API + 'so/' + id);
  }

}
