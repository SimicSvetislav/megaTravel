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
    });

  }

}
