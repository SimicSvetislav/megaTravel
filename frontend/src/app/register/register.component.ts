import { AuthService } from './../services/auth/auth.service';
import { SignUpInfo } from './../forms/registerForm';
import { User } from './../user';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';



export class RegistrationValidator {
  static validate(registrationFormGroup: FormGroup) {
    let password = registrationFormGroup.controls.password.value;
    let confirmPassword = registrationFormGroup.controls.confirmPassword.value;

    if (confirmPassword.length <= 0) {
      return null;
    }

    if(confirmPassword !== password) {
      return {
        doesMatchPassword: true
      };
    }

    return null;
  }
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registrationFormGroup : FormGroup;
  passwordFormGroup: FormGroup;

  user: User = new User();
  temp:User = new User();
  signupInfo : SignUpInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private formBuilder: FormBuilder,private router : Router,
              private authService: AuthService) { 
            this.passwordFormGroup = this.formBuilder.group({
            password: ['', Validators.required],
            confirmPassword: ['',Validators.required]
          }, {
          validator: RegistrationValidator.validate.bind(this)
          });

          this.registrationFormGroup = this.formBuilder.group({
            email: ['', Validators.required],
            passwordFormGroup: this.passwordFormGroup
          }); 
        }

  ngOnInit() {
  }

  home() {
    this.router.navigate(['/home']);
  }

  register() {
    this.signupInfo = new SignUpInfo(
      this.user.firstName,
      this.user.email,
      this.user.password,
      this.user.lastName,
      this.user.address,
      this.user.phoneNumber,
  )



  this.authService.signUp(this.signupInfo).subscribe(data => {
    this.temp = data;
    this.isSignedUp = true;
    this.isSignUpFailed = false;

    this.router.navigate(['/home']);
  })
  }

}
