import { AuthService } from './../services/auth/auth.service';
import { Token } from './../Token';
import { AuthLoginInfo } from './../forms/loginForm';
import { Router } from '@angular/router';
import { User } from './../user';
import { TestService } from './../services/test.service';
import { Component, OnInit } from '@angular/core';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {


  user: User = new User();
  constructor(private router: Router,private testing: TestService,private authService: AuthService) { }
  str: String="";

  private loginInfo : AuthLoginInfo;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  token: Token = new Token();

  ngOnInit() {
  }

  home() {
    this.router.navigate(['/home']);
  }

  login() {
    alert("Usao!")

    this.loginInfo = new AuthLoginInfo(this.user.email,this.user.password);
    this.authService.attemptAuth(this.loginInfo).subscribe(data => {

      alert("Alertujem: " + data.accessToken);

    })

  /*  this.testing.test().subscribe(data => {
      this.str = JSON.stringify(data);
      alert("Vratio se sime: " + this.str);
    })*/
  }

}
