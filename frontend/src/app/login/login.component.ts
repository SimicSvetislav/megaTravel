import { TokenStorageService } from './../services/auth/token-storage.service';
import { AuthService } from './../services/auth/auth.service';
import { Token } from './../Token';
import { AuthLoginInfo } from './../forms/loginForm';
import { Router } from '@angular/router';
import { User } from './../user';
import { Component, OnInit } from '@angular/core';
import { HostListener } from '@angular/core';
import { EventBrokerService } from '../services/event-broker/event-broker.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {


  user: User = new User();
  constructor(private tokenService: TokenStorageService, 
              private eventBroker: EventBrokerService, 
              private router: Router,private authService: AuthService,
              private tokenStorage: TokenStorageService) { }
  str: String="";

  private loginInfo : AuthLoginInfo;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  token: Token = new Token();

  ngOnInit() {
    var user = this.tokenService.getUser();

    if (user!=null) {
      this.router.navigate(['/home']);
    }
  }

  home() {
    this.eventBroker.myEmit("refresh");
    this.router.navigate(['/home']);
  }

  login() {
    //alert("Usao!")

    this.loginInfo = new AuthLoginInfo(this.user.email,this.user.sifra);
    //alert("LOGIN: " + this.loginInfo.email + " + " + this.loginInfo.password);
    this.authService.attemptAuth(this.loginInfo).subscribe(data => {

      if(data.accessToken === undefined) {
        alert("Nesto nije u redu!");
      } else {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);
        this.tokenStorage.saveUser(data.user_id);
        this.tokenStorage.saveReserved(0);

        this.tokenStorage.saveRefresh(true);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.router.navigate(['/home']);
      }
    }, error => {
      console.log(error);
    });

  }

  // Callback za klik na back dugme
  /*@HostListener('window:popstate', ['$event'])
  onPopState(event) {
    this.eventBroker.myEmit("refresh");
  }*/

}
