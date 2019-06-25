import { Observable } from 'rxjs/Rx';
import { SearchObject } from './../../searchObject';
import { API_SEARCH } from './../../globals';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }

  search(sOjbect: SearchObject, user: any): Observable<any> {

    return this.http.post(API_SEARCH + user, sOjbect);
  }

}
