import { MessagesService } from './../services/chat/messages.service';
import { AgentService } from './../services/users/agent.service';
import { ChatService } from '../services/chat/chat.service';
import { Message } from './../message';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from '../services/auth/token-storage.service';
import { RbmService } from '../services/rbm/rbm.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {

  message: Message = new Message();
  messages: Array<Message> = new Array<Message>();
  chatArea = '';
  agentId: number;

  constructor(private router: Router, private token: TokenStorageService,
    private chatService: ChatService, private route: ActivatedRoute,
    private agentService: AgentService, private messagesService: MessagesService,
    private rbm: RbmService) {
    
    chatService.messages.subscribe(msg => {
      if (msg.text.startsWith('[INFO]')) {
        // INFO message
        console.log(msg.text.substring(6));
      } else {
        // Proveriti da li je poruka namenjena njemu
        if (msg.reservation === this.message.reservation && msg.receiver === this.message.sender) {
          this.chatArea += 'Agent: ' + msg.text + '\n';
        } else if (msg.reservation === this.message.reservation && msg.sender === this.message.sender) {
          // Ako klijent ima vise prozora otvorenih na istom cetu
          this.chatArea += 'You: ' + msg.text + '\n';
        }
      }
    });
  }

  ngOnInit() {

    var user = this.token.getUser();

    if (user==null) {
      this.router.navigate(['/home']);
    }

    // Cita se id rezervacije
    const id = +this.route.snapshot.params['id'];

    this.agentService.getByReservation(id).subscribe( data => {
      //this.agentId = data.id;
      this.agentId = 1
      this.message.receiver = data.id;
    }, error => console.log(error));

    this.messagesService.getByReservation(id).subscribe( data => {

      this.messages = data;

      data.forEach(element => {
        if (element.sender == user) {
          this.chatArea += 'You: ' + element.text + '\n';
        } else {
          this.chatArea += 'Agent: ' + element.text + '\n';
        }
      });

    }, error => console.log(error));

    // this.message.receiver = 1; // id primaoca
    this.message.sender = +user; // id posiljaoca
    this.message.reservation = id; // id rezervacije
    this.message.payload = null;
  }

  onSend() {
    if (!this.message.text || this.message.text.trim() === '') {
      return;
    }
  
    this.message.timestamp = new Date();

    this.rbm.filter(this.token.getUser(), {tekst: this.message.text}).subscribe( data => {
      if (data.includes("rejected")) {
        alert("Message rejected")
        return;
      }

      this.chatArea += 'You: ' + this.message.text + '\n';
     

      // Sending message
      this.chatService.messages.next(this.message);
      
      this.message.text = '';

    })

    
  }

}
