import { Component, OnInit } from '@angular/core';
import { AgentsService } from 'src/app/services/agents.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-agent-edit',
  templateUrl: './agent-edit.component.html',
  styleUrls: ['./agent-edit.component.scss']
})
export class AgentEditComponent implements OnInit {

  agent: any;

  constructor(private agentService: AgentsService, private route: ActivatedRoute) { }

  ngOnInit() {

    const id = this.route.snapshot.params['id'];

    this.agentService.getOne(id).subscribe(data => {
      this.agent = data;
      alert(data.id);
    }, error => console.log(error));

  }

}
