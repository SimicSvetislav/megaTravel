import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccomodationService {

  private getObjectCategoriesUrl = '';
  private getObjectTypesUrl = '';

  private getObjectUrl = '';
  private getObjectsUrl = '';
  private addObjectUrl = '';

  private getUnitUrl = '';
  private getUnitsUrl = '';
  private addUnitUrl = '';



  constructor(private http: HttpClient) { }

  getObjectCategories(): Observable<any> {
    return this.http.get(this.getObjectCategoriesUrl);
  }

  getObjectType(): Observable<any> {
    return this.http.get(this.getObjectTypesUrl);
  }

  getObject(): Observable<any> {
    return this.http.get(this.getObjectUrl);
  }

  getObjects(): Observable<any> {
    return this.http.get(this.getObjectsUrl);
  }

  addObject(): Observable<any> {
    return this.http.get(this.addObjectUrl);
  }

  getUnit(): Observable<any> {
    return this.http.get(this.getUnitUrl);
  }

  getUnits(): Observable<any> {
    return this.http.get(this.getUnitsUrl);
  }

  addUnit(): Observable<any> {
    return this.http.get(this.addUnitUrl);
  }
}
