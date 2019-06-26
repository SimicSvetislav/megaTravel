import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  private CLOUD_API = 'http://localhost:8010/rating-module/us-central1/';

  constructor(private http: HttpClient) {

  }

  averageGrade(room: number): Observable<any> {
    return this.http.get(this.CLOUD_API + 'averageGrade?room=' + room );
  }

  averageGradeObject(object: number): Observable<any> {
    return this.http.get(this.CLOUD_API + 'averageGradeObject?object=' + object );
  }

  getRatingsApproved(): Observable<any> {
    return this.http.get(this.CLOUD_API + 'getRatingsApproved');
  }

}
