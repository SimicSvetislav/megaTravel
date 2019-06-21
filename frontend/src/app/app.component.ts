import { AuthService } from './services/auth/auth.service';
import { UserService } from './services/users/user.service';
import { User } from './user';
import { TokenStorageService } from './services/auth/token-storage.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { EventBrokerService, IEventListener } from './services/event-broker/event-broker.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [EventBrokerService]
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'frontend';

  user: User = new User();

  ispis: string = "";

  id;
  boolLogIn: boolean = false;
  boolLogOff: boolean = false;


  private _myEventListener: IEventListener;

  constructor(private _eventBroker: EventBrokerService, private router: Router, private token: TokenStorageService, private userSer: UserService, private authSer: AuthService) {
    this._myEventListener = _eventBroker.listen("refresh", () => {
      this.ngOnInit();
    });
  }

  ngOnInit(): void {

    this.id = this.token.getUser();

    if (this.id == null) {
      this.boolLogIn = false;
      this.boolLogOff = true;
    } else {
      this.boolLogIn = true;
      this.boolLogOff = false;

      this.userSer.getLogged(this.id).subscribe(data => {
        this.user = data;

        this.ispis = this.user.ime + " " + this.user.prezime;
      })
    }
  }

  public ngOnDestroy() {
    this._myEventListener.ignore();
  }

  signOut() {
    this.token.signOut();
    this.ngOnInit();
    this.router.navigate(['/login']);
  }

  login() {
    this.router.navigate(['/login'])
  }

  register() {
    this.router.navigate(['/register'])
  }

  refresh(e) {
    alert(e);
  }

}
