import { USER_API, API_USER } from './../globals';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(USER_API);
  }

  block(id: Number): Observable<any> {
    return this.http.get(USER_API + 'block/' + id);
  }

  activate(id: Number): Observable<any> {
    return this.http.get(USER_API + 'activate/' + id);
  }

  getOneById(id: Number): Observable<any> {
    return this.http.get(API_USER + 'admin/' + id);
  }

}
