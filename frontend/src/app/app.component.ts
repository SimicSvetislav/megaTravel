import { AuthService } from './services/auth/auth.service';
import { UserService } from './services/user/user.service';
import { User } from './user';
import { TokenStorageService } from './services/auth/token-storage.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'frontend';

  user: User = new User();

  ispis: string = "";

  id;
  boolLogIn: boolean = false;
  boolLogOff: boolean = false;

  constructor(private router: Router, private token: TokenStorageService, private userSer: UserService,private authSer: AuthService) {}

  ngOnInit() : void {

    this.id = this.token.getUser();

    if(this.id == null) {
      this.boolLogIn = false;
      this.boolLogOff = true;
    } else {
      this.boolLogIn = true;
      this.boolLogOff = false;

      this.userSer.getLogged(this.id).subscribe(data => {
        this.user = data;

        this.ispis += this.user.ime + " " + this.user.prezime;

      })
      
    }



  }


  signOut() {
    this.token.signOut();
    this.router.navigate(['/login']);
  }

  login() {
    this.router.navigate(['/login'])
  }

  register() {
    this.router.navigate(['/register'])
  }
}
