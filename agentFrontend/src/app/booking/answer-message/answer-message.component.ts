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
  }

  backTo() {
    this.back.emit();
  }

  replyTo() {
    this.message = undefined;
    this.reply.emit();
  }

}
