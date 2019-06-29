import { Route } from '@angular/compiler/src/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Agent } from './../../types';
import { AgentsService } from 'src/app/services/agents.service';
import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/services/auth/token-storage.service';

@Component({
  selector: 'app-agent-add',
  templateUrl: './agent-add.component.html',
  styleUrls: ['./agent-add.component.scss']
})
export class AgentAddComponent implements OnInit {

  agent: Agent = new Agent();

  constructor(private tokenService: TokenStorageService, private router: Router,
              private service: AgentsService, private toastr: ToastrService) {
  }

  ngOnInit() {

    var user = this.tokenService.getUser();

    if (user==null) {
      this.router.navigate(['/login']);
    }

  }

  onSubmit() { 
    this.service.post(this.agent).subscribe(data => {
      this.router.navigate(['/agents']);
    }, (error: Response) => {
      switch(error.status) {
        case 409:
          this.toastr.error('Agent with same email already exists');
          break;
        default:
          this.toastr.error('Unknown error occurs');
          break;
      }
    });
  }

}
