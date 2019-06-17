import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AgentsService } from '../services/agents.service';

@Component({
  selector: 'app-agents',
  templateUrl: './agents.component.html',
  styleUrls: ['./agents.component.scss']
})
export class AgentsComponent implements OnInit {

  agents: any;

  constructor(private agentService: AgentsService, private router: Router) { }

  ngOnInit() {
    this.agentService.getAgents().subscribe(data => {
      this.agents = data;
    }, error => console.log(error));
  }

  add() {
    this.router.navigateByUrl('/agents/add');
  }

}
