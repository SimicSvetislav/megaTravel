import { CLOUD_API } from './../../globals';
import { API_USER, API_RESERVATION } from '../../globals';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Rating } from 'src/app/rating';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private RESERVATION_API = '//localhost:8122/';
  private USER_API = '//localhost:8152/';

  constructor(private http: HttpClient) { }

  getAllReservationsById(id): Observable<any> {
    return this.http.get(API_RESERVATION + 'user/' + id);
  }

  getLogged(id): Observable<any> {
    return this.http.get(API_USER + 'user/' + id);
  }

  setRate(rating: Rating): Observable<any> {
    //let headers = new HttpHeaders();
    //headers.set('COntent-type', 'application/json');
    return this.http.post(CLOUD_API + 'postRating', rating);

  }

  getSmestajnaJedinica(id): Observable<any> {
    return this.http.get(API_RESERVATION + 'sj/' + id);
  }


}
