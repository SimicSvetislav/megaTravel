import { CLOUD_API, CLOUD_APPROVAL, CLOUD_AVERAGE, CLOUD_API2 } from './../../globals';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Rating } from 'src/app/rating';

@Injectable({
  providedIn: 'root'
})
export class CloudService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {

    
    return this.http.get(CLOUD_API2 + 'getRatings');
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
    return this.http.post(CLOUD_API2 + 'postRating', rating);
  }
}
