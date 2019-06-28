import { RatingService } from './../../service/rating/rating.service';
import { CommentMessageService } from './../../service/comment-message.service';
import { Komentar } from './../../model/rezervacija/komentar.model';
import { Component, OnInit, Input } from '@angular/core';
import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { analyzeAndValidateNgModules } from '@angular/compiler';

@Component({
  selector: 'app-view-unit-comments',
  templateUrl: './view-unit-comments.component.html',
  styleUrls: ['./view-unit-comments.component.css']
})
export class ViewUnitCommentsComponent implements OnInit {

  @Input()
  unit: SmestajnaJedinica;

  comments: Komentar[] = [];

  constructor(private commentMessageService: CommentMessageService, private ratingService: RatingService) { }

  ngOnInit() {
    this.ratingService.getRatingsApproved().subscribe(data => {
      const komentari: Komentar[] = data;

      this.unit.id = 13;
      const id = this.unit.id;
      this.comments = komentari.filter(c => id === c.room);
      console.log(this.comments);
    });
  }

}
