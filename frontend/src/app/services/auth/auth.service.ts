import { AuthLoginInfo } from './../../forms/loginForm';
import { SignUpInfo } from './../../forms/registerForm';
import { JwtResponse } from './../../jwt-response';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = '//localhost:8152/api/auth/signin/';
  private signupUrl = '//localhost:8152/api/auth/signup/';
  private url = '//localhost:8152/api/test/test';
  
  constructor(private http: HttpClient) {
  }

  test() {
    alert("test");
    return this.http.get(this.url)
  }

  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    alert("LDAFKJDSLKFJ");
    return this.http.post<JwtResponse>(this.loginUrl + 'user', credentials, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<any> {
    return this.http.post<string>(this.signupUrl + 'user', info, httpOptions);
  }
}
