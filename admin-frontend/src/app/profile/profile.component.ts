import { Message } from './../types';
import { TokenStorageService } from './../services/auth/token-storage.service';
import { UsersService } from './../services/users.service';
import { User } from './../user';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  user: User = new User();
  id;

  constructor(private userSer: UsersService,
              private tokenStorage: TokenStorageService) { }

  ngOnInit() {
    this.id = this.tokenStorage.getUser();
    this.userSer.getOneById(this.id).subscribe(data => {
      this.user = data;
    });

    
  }

}
