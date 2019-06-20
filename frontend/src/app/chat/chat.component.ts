import { User } from './../user';
import { Message } from './../message';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {

  constructor(private toastr: ToastrService) { }

  url = 'ws://localhost:7878/';
  connection = new WebSocket(this.url);
  message: Message = new Message();
  user: User = new User();
  chatArea = '';

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
    if (!this.message.text) {
      return;
    }
    this.toastr.success("Message sent");
    this.chatArea += "You: " + this.message.text + '\n';
    this.message.timestamp = new Date();
    this.message.payload = null;
    this.message.receiver = 1; // id primaoca
    this.message.sender = this.user.id; // id posoljaoca
    this.message.reservation = 1; // id rezervacije
    this.connection.send(JSON.stringify(this.message));
    this.message = new Message();
  }

}
