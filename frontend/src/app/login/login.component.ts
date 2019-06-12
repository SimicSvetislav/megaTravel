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
  str: String="";
  ngOnInit() {
  }

  home() {
    this.router.navigate(['/home']);
  }

  login() {
    alert("Usao!")
    this.testing.test().subscribe(data => {
      this.str = JSON.stringify(data);
      alert("Vratio se sime: " + this.str);
    })
  }

}
