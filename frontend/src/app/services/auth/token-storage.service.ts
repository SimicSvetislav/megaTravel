import { SearchObject } from './../../searchObject';
import { Injectable } from '@angular/core';

const TOKEN_KEY = 'AuthToken';
const USERNAME_KEY = 'AuthUsername';
const AUTHORITIES_KEY = 'AuthAuthorities';
const USER_KEY = 'AuthUser;';
const RESERVED = 'Reserved;';

const REFRESH = "Refresh";

const SEARCH = "Search"

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  private roles: Array<string> = [];
  constructor() { }

  public signOut() {
    window.sessionStorage.clear();
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUsername(username: string) {
    window.sessionStorage.removeItem(USERNAME_KEY);
    window.sessionStorage.setItem(USERNAME_KEY, username);
  }

  public getUsername(): string {
    return sessionStorage.getItem(USERNAME_KEY);
  }

  public saveAuthorities(authorities: string[]) {
    window.sessionStorage.removeItem(AUTHORITIES_KEY);
    window.sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
  }

  public getAuthorities(): string[] {
    this.roles = [];

    if (sessionStorage.getItem(TOKEN_KEY)) {
      JSON.parse(sessionStorage.getItem(AUTHORITIES_KEY)).forEach(authority => {
        this.roles.push(authority.authority);
      });
    }

    return this.roles;
  }

  public saveUser(user: number) {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser() : string {
    return sessionStorage.getItem(USER_KEY);
  }

  public saveReserved(num: number) {
    window.sessionStorage.removeItem(RESERVED);
    window.sessionStorage.setItem(RESERVED, JSON.stringify(num));
  }

  public getReserved() : string {
    return sessionStorage.getItem(RESERVED);
  }

  public saveRefresh(refresh: boolean) {
    window.sessionStorage.removeItem(REFRESH);
    window.sessionStorage.setItem(REFRESH, JSON.stringify(refresh));
  }

  public getRefresh() : string {
    return sessionStorage.getItem(REFRESH);
  }

  public saveSearch(item: SearchObject) {
    window.sessionStorage.removeItem(SEARCH);
    window.sessionStorage.setItem(SEARCH, JSON.stringify(item));
  }

  public getSearch(): string {
    return sessionStorage.getItem(SEARCH);
  }



}
