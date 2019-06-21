import { CommentMessageService } from './../../service/comment-message.service';
import { Component, OnInit } from '@angular/core';
import { Poruka } from 'src/app/model/rezervacija/poruka.model';

@Component({
  selector: 'app-view-all-messages',
  templateUrl: './view-all-messages.component.html',
  styleUrls: ['./view-all-messages.component.css']
})
export class ViewAllMessagesComponent implements OnInit {

  activeTab: any;
  displayDirection: any;

  selectedMessage: Poruka;

  constructor(private commentMessageService: CommentMessageService) { }

  ngOnInit() {
    this.activeTab = 'inbox';
    this.displayDirection = 'receive';
  }

  changeActiveTab(newActiveTab: string) {
    this.activeTab = newActiveTab;
    this.selectedMessage = undefined;
  }

  back() {

  }

  selectedMessageChanged(poruka: Poruka) {
    // this.displayDirection =  this.activeTab === 'inbox' ? 'receive' : this.displayDirection;
    this.selectedMessage = poruka;
  }

  backFromView() {

  }

  replyFromView() {

    this.displayDirection = 'answer';
  }

  backFromAnswer() {

  }

  replyFromAnswer() {
    // this.displayDirection = 'answer';
    this.displayDirection = 'receive';
    this.commentMessageService.answerMessage(this.selectedMessage).subscribe();
    this.selectedMessage = undefined;
  }

}
