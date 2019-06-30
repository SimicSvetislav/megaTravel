import { Admin } from './../types';
import { ADMIN_API } from './../globals';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminsService {
  
  changePass(np: import("../NewPassword").NewPassword, id: string) {
    return this.http.put(ADMIN_API + id + "/pass", np);
  }

  constructor(private http: HttpClient) { }

  update(admin: Admin): Observable<any> {
    return this.http.put(ADMIN_API, admin);
  }

  getAll():Observable<any> {
    return this.http.get(ADMIN_API);
  }

  remove(id: number): Observable<any> {
    return this.http.delete(ADMIN_API + id);
  }

  add(admin: Admin): Observable<any> {
    return this.http.post(ADMIN_API, admin);
  }

}
