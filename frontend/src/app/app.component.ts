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

  id;
  boolLogIn: boolean = false;
  boolLogOff: boolean = false;

  constructor(private router: Router, private token: TokenStorageService) {}

  ngOnInit() : void {

    this.id = this.token.getUser();

    if(this.id == null) {
      this.boolLogIn = false;
      this.boolLogOff = true;
    } else {
      this.boolLogIn = true;
      this.boolLogOff = false;
      
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
