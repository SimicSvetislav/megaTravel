import { Message } from '../../booking/chat/message';
import { Injectable } from '@angular/core';
// tslint:disable-next-line:import-blacklist
import { Observable, Subject } from 'rxjs/Rx';
import { SocketService } from './socket.service';

const CHAT_URL = 'ws://localhost:7878/';

@Injectable()
export class ChatService {
  public messages: Subject<Message>;

  constructor(wsService: SocketService) {
    this.messages = <Subject<Message>>wsService.connect(CHAT_URL).map(
      (response: MessageEvent): Message => {
        const data = JSON.parse(response.data);
        return {
          payload: data.payload,
          receiver: data.receiver,
          timestamp: data.timestamp,
          reservation: data.reservation,
          sender: data.sender,
          text: data.text
        };
      }
    );
  }
}
