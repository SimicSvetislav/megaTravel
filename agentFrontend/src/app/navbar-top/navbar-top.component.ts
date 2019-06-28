import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../service/auth/toke-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar-top',
  templateUrl: './navbar-top.component.html',
  styleUrls: ['./navbar-top.component.css']
})
export class NavbarTopComponent implements OnInit {

  isUserLogged: boolean;

  constructor(private tokenStorage: TokenStorageService, private router: Router) { }

  ngOnInit() {
    // if (this.tokenStorage.getToken()) {
    //   this.isUserLogged = true;
    // } else {
    //   this.isUserLogged = false;
    // }
    this.isUserLogged = this.tokenStorage.isLogged();
    this.tokenStorage.isLoggedUser.subscribe(value => {
      this.isUserLogged = value;
    });
  }

  signOut() {
    this.tokenStorage.signOut();
    this.router.navigate(['home']);
  }

}
