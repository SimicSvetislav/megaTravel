import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Agent } from './../../types';
import { AgentsService } from 'src/app/services/agents.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-agent-add',
  templateUrl: './agent-add.component.html',
  styleUrls: ['./agent-add.component.scss']
})
export class AgentAddComponent implements OnInit {

  agent: Agent = new Agent();

  title = 'Angular Form Validation Tutorial';
  angForm: FormGroup;

  constructor(private service: AgentsService, private toastr: ToastrService, private router: Router, ) {
  }

  ngOnInit() {

  }

  onSubmit() { 
    this.service.post(this.agent).subscribe(data => {
      this.router.navigate(['/agents']);
    }, error => this.toastr.error(error));
  }

}
