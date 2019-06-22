import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { AuthLoginInfo } from 'src/app/model/forms/login-form.model';
import { SignUpInfo } from 'src/app/model/forms/register-form.model';
import { JwtResponse } from 'src/app/model/jwt-response.model';
import { AppConfigService } from '../app-config.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/api/auth/signin';
  private signupUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/api/auth/signup';

  constructor(private http: HttpClient) {
  }



  attemptAuth(credentials: AuthLoginInfo): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(info: SignUpInfo): Observable<any> {
    return this.http.post<string>(this.signupUrl, info, httpOptions);
  }
}
