import { Component, OnInit } from '@angular/core';
import { AppConfigService } from '../service/app-config.service';

@Component({
  selector: 'app-page-not-found',
  templateUrl: './page-not-found.component.html',
  styleUrls: ['./page-not-found.component.css']
})
export class PageNotFoundComponent implements OnInit {

  constructor() { }

  tekst: string;

  ngOnInit() {
    this.tekst = AppConfigService.settings.backend.serverPort;

  }

}
