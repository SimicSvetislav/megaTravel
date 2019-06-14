import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Poruka } from 'src/app/model/rezervacija/poruka.model';

@Component({
  selector: 'app-view-message',
  templateUrl: './view-message.component.html',
  styleUrls: ['./view-message.component.css']
})
export class ViewMessageComponent implements OnInit {

  @Input()
  message: Poruka;

  @Input()
  activeTab: any;

  @Output()
  back = new EventEmitter();

  @Output()
  reply = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  backTo() {
    this.back.emit();
  }

  replyTo() {
    this.reply.emit();
  }

}
