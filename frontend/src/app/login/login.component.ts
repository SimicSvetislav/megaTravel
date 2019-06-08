import { User } from './../user';
import { TestService } from './../services/test.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {


  user: User = new User();
  constructor(private router: Router,private testing: TestService) { }

  ngOnInit() {
  }

  home() {
    this.router.navigate(['/home']);
  }

  login() {
    alert("Usao!")
    this.testing.test("Puno pozdrava!");
  }

}
