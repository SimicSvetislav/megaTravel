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

  constructor(private service: AgentsService, private toastr: ToastrService, private router: Router,) { }

  agent: Agent = new Agent();

  ngOnInit() {

  }

  add() {

    this.service.post(this.agent).subscribe( data => {
      this.router.navigate(['/agents']);
    }, error => this.toastr.error(error));

  }

}
