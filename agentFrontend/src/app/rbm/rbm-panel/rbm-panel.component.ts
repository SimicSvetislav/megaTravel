import { ToastrService } from 'ngx-toastr';
import { TokenStorageService } from './../../service/auth/toke-storage.service';
import { Component, OnInit } from '@angular/core';
import { RbmService } from 'src/app/service/rbm.service';

@Component({
  selector: 'app-rbm-panel',
  templateUrl: './rbm-panel.component.html',
  styleUrls: ['./rbm-panel.component.css']
})
export class RbmPanelComponent implements OnInit {

  word: string = '';
  
  pp1: number;
  pp2: number=1;

  pr1: number;
  pr2: number;

  id;

  constructor(private rbmService: RbmService, private token: TokenStorageService,
              private toastr: ToastrService) { }

  ngOnInit() {
    this.id = this.token.getUser();

    if (!this.id) {
      this.id = 1;
    }

  }

  forbid() {
    this.rbmService.forbid(this.word, this.id).subscribe( data => {
      this.toastr.success(data)
    }, error => console.log(error))
  }

  submit() {
    this.rbmService.submit(this.pp1, this.pp2, this.id).subscribe( data => {
      this.toastr.success(data)
    }, error => console.log(error))
  }

  add() {
    this.rbmService.add(this.pr1, this.pr2, this.id).subscribe( data => {
      this.toastr.success(data)
    }, error => console.log(error))
  }

}
