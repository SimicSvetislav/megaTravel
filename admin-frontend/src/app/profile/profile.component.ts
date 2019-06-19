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
    })

    const url = 'ws://localhost:7878/'
    const connection = new WebSocket(url)

    connection.onopen = () => {
      alert("Connection established")
      this.user.id=5;
      connection.send(JSON.stringify(this.user));
    }

    connection.onerror = error => {
      console.log(`WebSocket error: ${error}`)
    }

    connection.onmessage = e => {
      console.log(e.data)
    }


  }

}
