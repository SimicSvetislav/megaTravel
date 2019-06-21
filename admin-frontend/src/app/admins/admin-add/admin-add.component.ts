import { ADMIN_API } from './../../globals';
import { Admin } from './../../types';
import { AdminsService } from './../../services/admins.service';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/services/auth/token-storage.service';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-admin-add',
  templateUrl: './admin-add.component.html',
  styleUrls: ['./admin-add.component.scss']
})
export class AdminAddComponent implements OnInit {

  admin: Admin = new Admin();

  constructor(private tokenService: TokenStorageService, private router: Router,
              private service: AdminsService, private toastr: ToastrService) { }

  ngOnInit() {
    var user = this.tokenService.getUser();

    if (user==null) {
      this.router.navigate(['/login']);
    }
  }

  onSubmit() { 
    this.service.add(this.admin).subscribe(data => {
      this.router.navigate(['/admins']);
    }, error => this.toastr.error(error));
  }

}
