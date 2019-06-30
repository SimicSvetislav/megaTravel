import { CloudService } from './../services/cloud.service';
import { AdminsService } from './../services/admins.service';
import { Message, Admin, Rating } from './../types';
import { TokenStorageService } from './../services/auth/token-storage.service';
import { UsersService } from './../services/users.service';
import { User } from './../user';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EventBrokerService } from '../services/event-broker.service';
import { Toast, ToastrService } from 'ngx-toastr';
import { NewPassword } from '../NewPassword';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  user: Admin = new Admin();
  id;
  np = new NewPassword();

  constructor(private userSer: UsersService, private router: Router, private adminService: AdminsService,
              private tokenService: TokenStorageService, private eventBroker: EventBrokerService,
              private toastr: ToastrService, private c: CloudService) { }

  ngOnInit() {

    /*this.c.postRating(new Rating()).subscribe( data => {
      alert("Prosao");
    }, error => console.log(error))*/

    if (this.tokenService.getRefresh()==='true') { 
      this.tokenService.saveRefresh(false);
      this.eventBroker.myEmit("refresh");
    }

    var user = this.tokenService.getUser();

    if (user == null) {
      this.router.navigate(['/login']);
    }

    this.id = this.tokenService.getUser();
    this.userSer.getOneById(this.id).subscribe(data => {
      this.user = data;
    });
  }

  onSubmit() {
    
    this.adminService.update(this.user).subscribe( data => {
      this.toastr.success("Successfully updated");
    }, error =>  {
      console.log(error);
      this.toastr.error("Update failed");
    });

  }

  changePass() {
    this.adminService.changePass(this.np, this.tokenService.getUser()).subscribe( data => {
      this.toastr.success("Successfully changed password");
    }, (error: Response) =>  {
      console.log(error);
      this.toastr.error("Password change failed");
    });
  }

}
