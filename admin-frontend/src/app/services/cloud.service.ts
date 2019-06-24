import { Rating } from './../types';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CLOUD_API, CLOUD_NOT_APPROVED, CLOUD_APPROVAL, CLOUD_AVERAGE } from './../globals';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CloudService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {

    
    return this.http.get(CLOUD_API + 'getRatings');
  }

  getNotApproved(): Observable<any> {

    return this.http.get(CLOUD_NOT_APPROVED);
  }

  approve(payload: any): Observable<any> {

    let headers = new HttpHeaders();
    headers.set('Content-type', 'application/json');
    return this.http.put(CLOUD_APPROVAL, payload, {headers});
  }

  averageRoom(room: number): Observable<any> {

    let headers = new HttpHeaders();
    headers.set('Content-type', 'application/json'); 
    return this.http.get(CLOUD_AVERAGE + '?room=' + room );
  }

  postRating(rating: Rating): Observable<any> {
    //let headers = new HttpHeaders();
    //headers.set('Content-type', 'application/json');
    return this.http.post(CLOUD_API + 'postRating', rating);
  }

}
