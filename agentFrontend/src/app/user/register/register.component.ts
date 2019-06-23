import { AuthService } from './../../service/auth/auth.service';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { SignUpInfo } from 'src/app/model/forms/register-form.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  username: string;
  password: string;
  firstName: string;
  lastName: string;
  address: string;
  telephone: string;


  constructor(private toastr: ToastrService, private authService: AuthService, private router: Router) { }

  ngOnInit() {
  }

  register() {
    this.toastr.success('usao ovde u register');
    this.authService.signUp(new SignUpInfo(this.firstName, this.username, this.password, this.lastName, this.address, this.telephone))
    .subscribe(data => {
      this.router.navigate(['/home']);
    }, (error: Response) => {

    });
  }

}
