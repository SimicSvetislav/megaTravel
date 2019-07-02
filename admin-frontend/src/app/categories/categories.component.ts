import { Router } from '@angular/router';
import { CategorySm } from './../types';
import { CategoriesService } from './../services/categories.service';
import { Component, OnInit } from '@angular/core';
import { Type } from '../types';
import { ToastrService } from 'ngx-toastr';
import { TokenStorageService } from '../services/auth/token-storage.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent implements OnInit {

  constructor(private service: CategoriesService, private toastr: ToastrService,
              private tokenService: TokenStorageService, private router: Router) { }

  cats: CategorySm[] = new Array<CategorySm>();
  newCat: CategorySm = new CategorySm();

  ngOnInit() {

    var user = this.tokenService.getUser();

    if (user==null) {
      this.router.navigate(['/login']);
    }

    this.service.getAll().subscribe(data => {
      this.cats = data;
    }, error => console.log(error)); 
  }

  update(index: number) {
    const cat: CategorySm = this.cats[index]

    if (!cat.zvezdice) {
      this.toastr.warning('Please enter stars count');
      return;
    }

    if (!Number.isInteger(cat.zvezdice)) {
      alert(cat.zvezdice)
      this.toastr.warning('Stars count must be integer value');
      return;
    }

    this.service.update(cat).subscribe(data => {
      this.refresh()
      console.log("Updated " + data.id);
      this.toastr.success('Category updated');
    }, (error: Response) => {
      switch(error.status) {
        case 409:
          this.toastr.warning("Category can't be updated");
          break;
        default:
          this.toastr.error("Unknown error occured");
          break;
      }
      console.log(error)
    });

  }

  delete(id: number) {
    this.service.delete(id).subscribe(data => {
      this.refresh()
      console.log("Deleted " + data.id);
      this.toastr.success('Category deleted');
    }, (error: Response) => { 
      
      switch(error.status) {
        case 409:
          this.toastr.warning("Category can't be deleted");
          break;
        default:
          this.toastr.error("Unknown error occured");
          break;
      }
      
      console.log(error)
    });

  }

  refresh() {
    this.service.getAll().subscribe(data => {
      this.cats = data;
    }, error => console.log(error));
  }

  add() {
    if (!this.newCat.zvezdice) {
      this.toastr.warning("Please enter stars count");
      return;
    }

    if (!Number.isInteger(this.newCat.zvezdice)) {
      this.toastr.warning('Stars count must be integer value');
      return;
    }

    this.service.post(this.newCat).subscribe(data => {
      console.log("Added " + data.id);
      this.toastr.success('Category added');
      this.refresh();
      this.newCat = new CategorySm();
    }, (error: Response) => { 
      
      switch(error.status) {
        case 409:
          this.toastr.warning("Category already exists");
          break;
        default:
          this.toastr.error("Unknown error occured");
          break;
      }
      
      console.log(error)
    });

  }

}
