import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  title = 'admin-frontend';

  logged: Boolean = false;
  activeTab: String = 'Profile';

  constructor(private router: Router) {}

  ngOnInit(): void {
    
    this.router.navigateByUrl('/profile');

  }

  logout() {
    
  }

  login() {
    
  }

  navigate(tabName: string, path: string) {
    
    this.activeTab = tabName;

    this.router.navigateByUrl(path);
    
    return false;

  }

}
