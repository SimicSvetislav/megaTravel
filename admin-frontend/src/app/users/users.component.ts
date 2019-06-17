import { AGENT_API } from './../globals';
import { UsersService } from './../services/users.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  constructor(private service: UsersService) { }

  users: any;

  ngOnInit() {

    this.service.getAll().subscribe(data => {
      this.users = data;
    }, error => console.log(error));

  }

  block(id: Number) {
    this.service.block(id).subscribe(user => {
      console.log('Blocked ' + user.id);
      this.service.getAll().subscribe(data => {
        this.users = data;
      }, error => console.log(error));
    }, error => console.log(error));
  }

  activate(id: Number) {
    this.service.activate(id).subscribe(user => {
      console.log('Activated ' + user.id)
      this.service.getAll().subscribe(data => {
        this.users = data;
      }, error => console.log(error));
    }, error => console.log(error));
  }
}
