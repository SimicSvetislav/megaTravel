import { USER_API, API_USER } from './../globals';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
 
  /*categorize() {
    return this.http.get(RBM_API + 'catUsr', { responseType: 'text' });
  }

  categorizeAcc() {
    return this.http.get(RBM_API + 'catAcc', { responseType: 'text' });
  }*/

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(USER_API);
  }

  block(id: number): Observable<any> {
    return this.http.get(USER_API + 'block/' + id);
  }

  activate(id: number): Observable<any> {
    return this.http.get(USER_API + 'activate/' + id);
  }

  getOneById(id: number): Observable<any> {
    return this.http.get(API_USER + 'admin/' + id);
  }

  remove(id: number): Observable<any> {
    return this.http.delete(USER_API + id);
  }

}
