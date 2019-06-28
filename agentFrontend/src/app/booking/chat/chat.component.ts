import { UserService } from './../../service/user.service';
import { ReservationsService } from './../../service/reservations.service';
import { MessagesService } from '../../service/chat/messages.service';
import { ChatService } from '../../service/chat/chat.service';
import { Message } from './message';
import { TokenStorageService } from './../../service/auth/toke-storage.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Route } from '@angular/router';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  message: Message = new Message();
  messages: Array<Message> = new Array<Message>();
  chatArea = '';
  clientId: number;

  constructor(private tokenService: TokenStorageService, private router: Router,
              private chatService: ChatService, private route: ActivatedRoute,
              private messagesService: MessagesService, private reservationsService: ReservationsService,
              private userService: UserService) {
                chatService.messages.subscribe(msg => {
                  if (msg.text.startsWith('[INFO]')) {
                    // INFO message
                    console.log(msg.text.substring(6));
                  } else {
                    // Proveriti da li je poruka namenjena njemu
                    if (msg.reservation === this.message.reservation && msg.receiver === this.message.sender) {
                      this.chatArea += 'Client: ' + msg.text + '\n';
                    } else if (msg.reservation === this.message.reservation && msg.sender === this.message.sender) {
                      // Ako klijent ima vise prozora otvorenih na istom cetu
                      this.chatArea += 'You: ' + msg.text + '\n';
                    }
                  }
                });
              }

  ngOnInit() {

    let agent = '22'; // test     //soap poziv da se vrati info a agentu sa pravim id
    this.userService.getAgent().subscribe( data => {
      agent = data.id;
    }
    );

    // Cita se id rezervacije
    const resId = +this.route.snapshot.params['resId'];

    this.reservationsService.getUserByReservation(resId).subscribe( data => {
      this.clientId = data.id;
      this.message.receiver = data.id;
    }, error => console.log(error));

    this.messagesService.getByReservation(resId).subscribe( data => {

      this.messages = data;

      data.forEach(element => {
        if (element.sender === agent) {
          this.chatArea += 'You: ' + element.text + '\n';
        } else {
          this.chatArea += 'Client: ' + element.text + '\n';
        }
      });

    }, error => console.log(error));

    // this.message.receiver = 1; // id primaoca
    this.message.sender = +agent; // id posiljaoca
    this.message.reservation = resId; // id rezervacije
    this.message.payload = null;

  }

  onSend() {
    if (!this.message.text || this.message.text.trim() === '') {
      return;
    }

    this.chatArea += 'You: ' + this.message.text + '\n';
    this.message.timestamp = new Date();

    // Sending message
    this.chatService.messages.next(this.message);

    this.message.text = '';
  }

}
