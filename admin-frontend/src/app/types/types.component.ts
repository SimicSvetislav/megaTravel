import { TypesService } from './../services/types.service';
import { Component, OnInit } from '@angular/core';
import { Type } from '../types';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-types',
  templateUrl: './types.component.html',
  styleUrls: ['./types.component.scss']
})
export class TypesComponent implements OnInit {

  constructor(private service: TypesService, private toastr: ToastrService) { }

  types: Type[] = new Array<Type>();
  newType: Type = new Type();

  ngOnInit() {
    this.service.getAll().subscribe(data => {
      this.types = data;
    }, error => console.log(error)); 
  }

  update(index: number) {
    let type = this.types[index] 

    if (!type.naziv) {
      this.toastr.warning("Please enter name");
      return;
    }

    this.service.update(type).subscribe(data => {
      this.refresh()
      console.log("Updated " + data.id);
      this.toastr.success('Type updated');
    }, error =>  { 
      console.log(error);
      this.toastr.error('Update failed');
    });
  }

  delete(id: number) {
    this.service.delete(id).subscribe(data => {
      this.refresh()
      console.log("Deleted " + data.id);
      this.toastr.success('Type deleted');
    }, error => console.log(error));
  }

  refresh() {
    this.service.getAll().subscribe(data => {
      this.types = data;
    }, error => console.log(error));
  }

  add() {
    if (!this.newType.naziv) {
      this.toastr.warning("Please enter name");
      return;
    }

    this.service.post(this.newType).subscribe(data => {
      console.log("Added " + data.id);
      this.toastr.success('Type added');
      this.refresh();
      this.newType = new Type();
    }, error => console.log(error));

  }

}
