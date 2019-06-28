import { TokenStorageService } from 'src/app/service/auth/toke-storage.service';
import { Injectable } from '@angular/core';
import { AppConfigService } from './app-config.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private syncUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/user/synchronize';

  private agentUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/user/mail/';


  private usersMS = 'http://localhost:8762/users/agent/';

  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) {
  }

  private genHeader(): HttpHeaders {
    return new HttpHeaders().set('Authorization', this.tokenStorageService.getToken());
  }

  syncData(): Observable<any> {
    const header: HttpHeaders = this.genHeader();
    return this.http.get(this.syncUrl);
  }

  getAgent(): Observable<any> {
    const mail: string = this.tokenStorageService.getUsername();
    return this.http.get(this.agentUrl + mail);
  }


}
