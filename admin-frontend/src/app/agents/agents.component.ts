import { Component, OnInit } from '@angular/core';

import { AgentsService } from '../services/agents.service'

@Component({
  selector: 'app-agents',
  templateUrl: './agents.component.html',
  styleUrls: ['./agents.component.scss']
})
export class AgentsComponent implements OnInit {

  agents: any;

  constructor(private agentService: AgentsService) { }

  ngOnInit() {
    this.agentService.getAgents().subscribe(data => {
      this.agents = data;
    });
  }

}
