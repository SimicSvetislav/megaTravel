import { ToastrService } from 'ngx-toastr';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AgentsService } from '../services/agents.service';
import { TokenStorageService } from '../services/auth/token-storage.service';

@Component({
  selector: 'app-agents',
  templateUrl: './agents.component.html',
  styleUrls: ['./agents.component.scss']
})
export class AgentsComponent implements OnInit {

  agents: any;

  constructor(private tokenService: TokenStorageService, 
              private agentService: AgentsService, private router: Router, 
              private toastr: ToastrService) { }

  ngOnInit() {

    var user = this.tokenService.getUser();

    if (user==null) {
      this.router.navigate(['/login']);
    }

    this.agentService.getAgents().subscribe(data => {
      this.agents = data;
    }, error => console.log(error));
  }

  add() {
    this.router.navigateByUrl('/agents/add');
  }

  delete(id: number) {
    this.agentService.remove(id).subscribe(data => {
      this.toastr.success("Agent deleted");
      this.refresh();
    }, error => console.log(error));
  }

  refresh() {
    this.agentService.getAgents().subscribe(data => {
      this.agents = data;
    }, error => console.log(error));
  }

}
