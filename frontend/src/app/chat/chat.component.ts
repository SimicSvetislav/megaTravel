import { User } from './../user';
import { Message } from './../message';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {

  constructor() { }

  url = 'ws://localhost:7878/';
  connection = new WebSocket(this.url);
  message: Message = new Message();
  user: User = new User();

  ngOnInit() {

    this.connection.onopen = this.onOpen;
    this.connection.onerror = this.onError;
    this.connection.onmessage = this.onMessage;
  }

  onOpen() {
    console.log("Connection established")
  }

  onError(error) {
    console.log(`WebSocket error: ${error}`)
  }

  onMessage(e) {

    console.log(e.data)
    let msg:Message = JSON.parse(e.data);

    alert(msg.text);

  }

  onSend() {
    this.message.timestamp = new Date();
    this.message.payload = null;
    this.message.receiver = 1; // id primaoca
    this.message.sender = this.user.id; // id posoljaoca
    this.message.reservation = 1; // id rezervacije
    this.connection.send(JSON.stringify(this.message));
    this.message = new Message();
  }

}
