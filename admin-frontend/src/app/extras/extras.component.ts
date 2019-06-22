import { TokenStorageService } from './../services/auth/token-storage.service';
import { ToastrService } from 'ngx-toastr';
import { Component, OnInit, ɵɵcontainerRefreshEnd } from '@angular/core';
import { ExtrasService } from '../services/extras.service';
import { Extra } from '../types';
import { Router } from '@angular/router';

@Component({
  selector: 'app-extras',
  templateUrl: './extras.component.html',
  styleUrls: ['./extras.component.scss']
})
export class ExtrasComponent implements OnInit {

  constructor(private tokenService: TokenStorageService, private router: Router,
              private service: ExtrasService, private toastr: ToastrService) { }

  extras: Extra[] = new Array<Extra>();
  newExtra: Extra = new Extra();

  ngOnInit() {

    var user = this.tokenService.getUser();

    if (user==null) {
      this.router.navigate(['/login']);
    }

    this.service.getAll().subscribe(data => {
      /*data.forEach(element => {
        this.extras.set(element.id, element);
      });*/
      this.extras = data;
    }, error => console.log(error));
  }

  update(index: number) {
    let extra = this.extras[index]

    if (!extra.cena || !extra.ime) {
      this.toastr.warning("Please enter name and price");
      return;
    }

    if (extra.cena <= 0) {
      this.toastr.warning("Price must be positive number");
      return;
    }

    this.service.update(extra).subscribe(data => {
      this.refresh()
      console.log("Updated " + data.id);
      this.toastr.success('Extra updated');
    }, error =>  { 
      console.log(error);
      this.toastr.error('Update failed');
    });
  }

  delete(id: number) {
    this.service.delete(id).subscribe(data => {
      this.refresh()
      console.log("Deleted " + data.id);
      this.toastr.success('Extra deleted');
    }, error => console.log(error));
  }

  refresh() {
    this.service.getAll().subscribe(data => {
      this.extras = data;
    }, error => console.log(error));
  }

  add() {

    if (!this.newExtra.cena || !this.newExtra.ime) {
      this.toastr.warning("Please enter name and price");
      return;
    }

    if (this.newExtra.cena <= 0) {
      this.toastr.warning("Price must be positive number");
      return;
    }

    this.service.post(this.newExtra).subscribe(data => {
      console.log("Added " + data.id);
      this.toastr.success('Extra added');
      this.refresh();
      this.newExtra = new Extra();
    }, error => console.log(error));

  }

}
