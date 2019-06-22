import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth/auth.service';
import { TokenStorageService } from 'src/app/service/auth/toke-storage.service';
import { AuthLoginInfo } from 'src/app/model/forms/login-form.model';
import { Token } from '@angular/compiler';
import { Agent } from 'src/app/model/korisnik/agent.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: Agent;
  constructor(private router: Router, private authService: AuthService,
    private tokenStorage: TokenStorageService) { }
  str = '';

  private loginInfo: AuthLoginInfo;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
 // token: Token = new Token();

  username: string;
  password: string;

  ngOnInit() {
  }

  login() {

    this.loginInfo = new AuthLoginInfo(this.username, this.password);
    this.authService.attemptAuth(this.loginInfo).subscribe(data => {



      if (data.accessToken === undefined) {
        alert('Nesto nije u redu!');
      } else {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);
        this.tokenStorage.saveUser(data.user_id);
        this.tokenStorage.saveReserved(0);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
       /* this.roles = this.tokenStorage.getAuthorities();
        localStorage["sent"] = false;*/

        this.router.navigate(['/home']);


      }




    }, error => {
      console.log(error);

    });

  /*  this.testing.test().subscribe(data => {
      this.str = JSON.stringify(data);
      alert("Vratio se sime: " + this.str);
    })*/
  }

}
