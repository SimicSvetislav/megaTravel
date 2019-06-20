import { TokenStorageService } from './../services/auth/token-storage.service';
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
  constructor(private router: Router,private testing: TestService,private authService: AuthService,private tokenStorage: TokenStorageService) { }
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
    //alert("Usao!")

    this.loginInfo = new AuthLoginInfo(this.user.email,this.user.sifra);
    this.authService.attemptAuth(this.loginInfo).subscribe(data => {

      

      if(data.accessToken === undefined) {
        alert("Nesto nije u redu!");
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
      
    })

  /*  this.testing.test().subscribe(data => {
      this.str = JSON.stringify(data);
      alert("Vratio se sime: " + this.str);
    })*/
  }

}
