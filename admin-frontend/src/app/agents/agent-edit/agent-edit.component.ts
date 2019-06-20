import { Agent } from './../../types';
import { Component, OnInit } from '@angular/core';
import { AgentsService } from 'src/app/services/agents.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-agent-edit',
  templateUrl: './agent-edit.component.html',
  styleUrls: ['./agent-edit.component.scss']
})
export class AgentEditComponent implements OnInit {

  agent: Agent = new Agent();
  agentName: string;

  constructor(private service: AgentsService, private toastr: ToastrService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    this.service.getOne(id).subscribe(data => {
      this.agent = data;
      this.agentName = this.agent.korisnickoIme;
    });
  }

  onSubmit() {
    this.service.put(this.agent).subscribe(data => {
      this.toastr.success("Updated agent with id " + data.id);
      this.router.navigate(['/agents']);
    }, error => this.toastr.error(error));
  }
}
