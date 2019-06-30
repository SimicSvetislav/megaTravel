import { TokenStorageService } from './../services/auth/token-storage.service';
import { AuthService } from './../services/auth/auth.service';
import { SignUpInfo } from './../forms/registerForm';
import { User } from './../user';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';


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
  locationFormGroup: FormGroup;

  user: User = new User();
  temp:User = new User();
  signupInfo : SignUpInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';

  generateCoords = false;

  constructor(private formBuilder: FormBuilder,private router : Router,
              private authService: AuthService, private tokenService: TokenStorageService,
              private toastrService: ToastrService) { 
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

          this.locationFormGroup = this.formBuilder.group({
            location: ['', Validators.required],
            passwordFormGroup: this.passwordFormGroup
          })
        }

  ngOnInit() {
    var user = this.tokenService.getUser();

    if (user!=null) {
      this.router.navigate(['/home']);
    }
  }

  home() {
    this.router.navigate(['/home']);
  }


  lokacija;
  geoSirina;
  geoDuzina;

  register() {
    this.signupInfo = new SignUpInfo(
      this.user.ime,
      this.user.email,
      this.user.sifra,
      this.user.prezime,
      this.user.adresa,
      this.user.telefon,
      this.lokacija,
      this.geoDuzina,
      this.geoSirina
  )

  if (this.generateCoords) {
    this.signupInfo.geoDuzina = null;
    this.signupInfo.geoSirina = null;
  }

  if (!this.user.ime) {
    this.toastrService.warning("Please enter name");
    return;
  }

  if (!this.user.prezime) {
    this.toastrService.warning("Please enter surname");
    return;
  }

  if (!this.user.email) {
    this.toastrService.warning("Please enter email");
    return;
  }

  if (!this.user.sifra) {
    this.toastrService.warning("Please enter password");
    return;
  }

  if (this.user.sifra.length < 3) {
    this.toastrService.warning("Please enter password at least 3 characters long");
    return;
  }

  if (!this.lokacija) {
    this.toastrService.warning("Please enter location");
    return;
  }



  this.authService.signUp(this.signupInfo).subscribe(data => {


    this.temp = data;
    this.isSignedUp = true;
    this.isSignUpFailed = false;

    this.router.navigate(['/login'])
  }, error => {
    if(error.status == 405) {
      this.toastrService.error("Korisnik sa ovim e-mailom vec postoji");
      
    }
    console.log(error);
  })
  }

}
