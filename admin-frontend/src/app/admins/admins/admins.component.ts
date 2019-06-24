import { ToastrService } from 'ngx-toastr';
import { Admin } from './../../types';
import { AdminsService } from './../../services/admins.service';
import { TokenStorageService } from './../../services/auth/token-storage.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admins',
  templateUrl: './admins.component.html',
  styleUrls: ['./admins.component.scss']
})
export class AdminsComponent implements OnInit {

  admins: Array<Admin> = new Array();

  constructor(private tokenService: TokenStorageService, private router: Router,
    private adminsService: AdminsService, private toastr: ToastrService) { }

  user: string;

  ngOnInit() {
    this.user = this.tokenService.getUser();

    if (this.user == null) {
      this.router.navigate(['/login']);
    }

    this.adminsService.getAll().subscribe(data => {
      data.forEach(element => {
        if (element.id != this.user) {
          this.admins.push(element);
        }
      });

    }, error => console.log(error));

  }

  delete(id: number) {
    this.adminsService.remove(id).subscribe(data => {
      this.toastr.success("Deleted admin with id " + data.id);
      this.adminsService.getAll().subscribe(data => {
        this.admins = new Array();
        data.forEach(element => {
          if (element.id != this.user) {
            this.admins.push(element);
          }
        });
      }, error => console.log(error));
    }, error => console.log(error));
  }

  add() {
    this.router.navigateByUrl('/admins/add');
  }

}
