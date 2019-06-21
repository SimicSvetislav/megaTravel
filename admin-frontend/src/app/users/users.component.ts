import { ToastrService } from 'ngx-toastr';
import { UsersService } from './../services/users.service';
import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../services/auth/token-storage.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  constructor(private service: UsersService, private toastr: ToastrService,
              private tokenService: TokenStorageService, private router: Router) { }

  users: any;

  ngOnInit() {

    var user = this.tokenService.getUser();

    if (user==null) {
      this.router.navigate(['/login']);
    }

    this.service.getAll().subscribe(data => {
      this.users = data;
    }, error => console.log(error));

  }

  block(id: number) {
    this.service.block(id).subscribe(user => {
      console.log('Blocked ' + user.id);
      this.service.getAll().subscribe(data => {
        this.users = data;
      }, error => console.log(error));
    }, error => console.log(error));
  }

  activate(id: number) {
    this.service.activate(id).subscribe(user => {
      console.log('Activated ' + user.id)
      this.service.getAll().subscribe(data => {
        this.users = data;
      }, error => console.log(error));
    }, error => console.log(error));
  }

  delete(id: number) {
    this.service.remove(id).subscribe(user => {
      console.log('Deleted ' + user.id)
      this.toastr.success('User deleted')
      this.service.getAll().subscribe(data => {
        this.users = data;
      }, error => console.log(error));
    }, error => console.log(error));
  }

}
