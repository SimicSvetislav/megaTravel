import { CommentMessageService } from './../../service/comment-message.service';
import { Component, OnInit, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { Poruka } from 'src/app/model/rezervacija/poruka.model';

@Component({
  selector: 'app-view-all-messages-table',
  templateUrl: './view-all-messages-table.component.html',
  styleUrls: ['./view-all-messages-table.component.css']
})
export class ViewAllMessagesTableComponent implements OnInit, OnChanges {


  @Input()
  messagesType: string;

  // @Output()
  // selectedMessageChanged: EventEmitter<Poruka> = new EventEmitter();

  @Output()
  selectedMessageChanged = new EventEmitter();

  poruke: Poruka[];

  selectedRows: boolean[];

  constructor(private commentMessageService: CommentMessageService) { }

  ngOnInit() {
    this.commentMessageService.getAllReceivedMessages().subscribe(data => {
      this.poruke = data;

      this.selectedRows = new Array(this.poruke.length);

    });

    // this.genData();
  }

  genData() {
    this.poruke = [ {id: 1, timestamp: new Date(), text: 'lorem ipsum lfadjlfjalfjaldfjalkkjlfadjlkf',
    payload: '', reservation: undefined, sender: 2,  receiver: undefined },
  { id: 1, timestamp: new Date(), text: 'lorem ipsum lfadjlfjalfjaldfjalkkjlfadjlkf',
  payload: '', reservation: undefined, sender: 2, receiver: 3},
  {id: 1, timestamp: new Date(), text: 'lorem ipsum lfadjlfjalfjaldfjalkkjlfadjlkf',
  payload: '', reservation: undefined, sender: 2,  receiver: undefined },
  {id: 1, timestamp: new Date(), text: 'lorem ipsum lfadjlfjalfjaldfjalkkjlfadjlkf',
  payload: '', reservation: undefined, sender: 2, receiver: 3},
  {id: 1, timestamp: new Date(), text: 'lorem ipsum lfadjlfjalfjaldfjalkkjlfadjlkf',
  payload: '', reservation: undefined, sender: 2, receiver: 4},
  {id: 1, timestamp: new Date(), text: 'lorem ipsum lfadjlfjalfjaldfjalkkjlfadjlkf',
  payload: '', reservation: undefined, sender: 3,  receiver: undefined },
  {id: 1, timestamp: new Date(), text: 'lorem ipsum lfadjlfjalfjaldfjalkkjlfadjlkf',
  payload: '', reservation: undefined, sender: 4,  receiver: undefined },
  ];

    if ( this.messagesType === 'inbox') {
      this.poruke.push({id: 1, timestamp: new Date(), text: 'lorem ipsum lfadjlfjalfjaldfjalkkjlfadjlkf',
      payload: 'INBOX', reservation: undefined, sender: 2,  receiver: undefined });
    } else if (this.messagesType === 'sent') {
      this.poruke.push({id: 1, timestamp: new Date(), text: 'lorem ipsum lfadjlfjalfjaldfjalkkjlfadjlkf',
      payload: 'SENT', reservation: undefined, sender: 4,  receiver: undefined });
    } else {
      console.log('error');
    }

    this.selectedRows = new Array(this.poruke.length);
    for (let i = 0; i < this.poruke.length; ++i) {
      this.selectedRows[i] = false;
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    // if (changes.) {

    // }
    console.log('promena');
    this.genData();
  }

  select (p: Poruka, i: number) {
    this.selectedRows = this.selectedRows.fill(false, 0, this.selectedRows.length);
    // this.selectedRows.map((item) =>  { item =  false; console.log(item); }); // ne radi posao
    this.selectedRows[i] = true;
    console.log('selektovao');
    this.selectedMessageChanged.emit(p);
  }

}
