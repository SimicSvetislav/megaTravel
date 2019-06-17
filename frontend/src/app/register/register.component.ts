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

  constructor(private formBuilder: FormBuilder,private router : Router) { 
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

}
