import { UserService } from './../services/users/user.service';
import { AgentService } from './../services/users/agent.service';
import { ChatService } from '../services/chat/chat.service';
import { User } from './../user';
import { Message } from './../message';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from '../services/auth/token-storage.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {

  message: Message = new Message();
  chatArea = '';

  constructor(private router: Router, private token: TokenStorageService,
    private chatService: ChatService, private route: ActivatedRoute,
    private agentService: AgentService) {
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

    let user = this.token.getUser();

    if (user==null) {
      this.router.navigate(['/home']);
    }

    // Cita se id rezervacije
    const id = this.route.snapshot.params['id'];

    this.agentService.getByReservation(id).subscribe( data => {
      this.message.receiver = data.id;
    }, error => console.log(error));

    // this.message.receiver = 1; // id primaoca
    this.message.sender = +user; // id posiljaoca
    this.message.reservation = id; // id rezervacije
    this.message.payload = null;
  }

  onSend() {
    if (!this.message.text) {
      return;
    }

    this.chatArea += 'You: ' + this.message.text + '\n';
    this.message.timestamp = new Date();

    // Sending message
    this.chatService.messages.next(this.message);
    
    this.message.text = '';
  }

}
