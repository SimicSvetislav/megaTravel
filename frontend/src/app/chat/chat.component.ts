import { AgentService } from './../services/users/agent.service';
import { ChatService } from '../services/chat/chat.service';
import { User } from './../user';
import { Message } from './../message';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {

  message: Message = new Message();
  user: User = new User();
  chatArea = '';

  constructor(private chatService: ChatService, private route: ActivatedRoute, private agetnService: AgentService) {
    chatService.messages.subscribe(msg => {
      if (msg.text.startsWith('[INFO]')) {
        // INFO message
        console.log(msg.text.substring(6));
      } else {
        // Proveriti da li je poruka namenjena njemu
        this.chatArea += 'Agent: ' + msg.text + '\n';
      }
    });
  }

  ngOnInit() {

    // Cita se id rezervacije
    const id = this.route.snapshot.params['id'];

    this.agetnService.getByReservation(id).subscribe( data => {
      this.message.receiver = data.id;
    }, error => console.log(error));

    //this.message.receiver = 1; // id primaoca
    this.message.sender = this.user.id; // id posoljaoca
    this.message.reservation = id; // id rezervacije
    this.message.payload = null;
  }

  onSend() {
    if (!this.message.text) {
      return;
    }

    this.chatArea += "You: " + this.message.text + '\n';
    this.message.timestamp = new Date();

    // Sending message
    this.chatService.messages.next(this.message);
    
    this.message.text = '';
  }

}
