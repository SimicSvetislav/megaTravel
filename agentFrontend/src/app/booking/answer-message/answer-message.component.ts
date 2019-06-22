import { Poruka } from './../../model/rezervacija/poruka.model';
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-answer-message',
  templateUrl: './answer-message.component.html',
  styleUrls: ['./answer-message.component.css']
})
export class AnswerMessageComponent implements OnInit {

  @Input()
  message: Poruka;

  @Output()
  reply = new EventEmitter();

  @Output()
  back = new EventEmitter();

  constructor() { }

  ngOnInit() {
    const sender = this.message.receiver;
    const receiver = this.message.sender;
    this.message.sender = sender;
    this.message.receiver = receiver;
  }

  backTo() {
    this.back.emit();
  }

  replyTo() {
    // this.message = undefined;

    this.message.id = null;
    this.message.text = 'fjaldjfkalfjka';

    this.reply.emit();
  }

}
