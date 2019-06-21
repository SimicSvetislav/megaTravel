import { ToastrService } from 'ngx-toastr';
import { CloudService } from './../services/cloud.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../services/auth/token-storage.service';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.scss']
})
export class CommentsComponent implements OnInit {

  constructor(private tokenService: TokenStorageService, private router: Router,
              private cloudService: CloudService, private toastr: ToastrService) { }

  ratings: Array<any>;
  count = -1;

  ngOnInit() {

    var user = this.tokenService.getUser();

    if (user == null) {
      this.router.navigate(['/login']);
    }

    this.cloudService.getNotApproved().subscribe( data => {
      this.ratings = data;
      this.count = this.ratings.length;
    }, error => console.log(error));

  }

  approve(id: string) {

    this.cloudService.approve({id: id}).subscribe( () => {
      this.toastr.success("Comment approved");
      this.cloudService.getNotApproved().subscribe( data => {
        this.ratings = data;
        this.count = this.ratings.length;
      }, error => console.log(error));
    }, error => console.error(error));

  }

}
