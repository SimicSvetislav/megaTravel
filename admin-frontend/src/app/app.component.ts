import { TokenStorageService } from './services/auth/token-storage.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  title = 'admin-frontend';

  logged: Boolean = false;
  activeTab: String = 'Profile';
  id;

  constructor(private router: Router, private token: TokenStorageService) { }

  ngOnInit(): void {
    
    this.router.navigateByUrl('/profile');
    
    this.id = this.token.getUser();
    if(this.id == null) {
      this.logged = false;
    } else {
      this.logged = true;
    }
  }

  logout() {
    this.token.signOut();
    this.router.navigate(['/login']);
  }

  login() {
    this.router.navigate(['/login']);
  }

  register() {
    this.router.navigate(['/register']);
  }

  navigate(tabName: string, path: string) {

    this.activeTab = tabName;
    
    this.router.navigateByUrl(path);

    return false;

  }

}
