import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { AppConfigService } from './app-config.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AccomodationService {

  private getObjectCategoriesUrl = '';
  private getObjectTypesUrl = '';

  private getObjectBaseUrl = '';
  private getObjectsUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/accomodation/object';
  private addObjectUrl = '';

  private getUnitBaseUrl = '';
  private getUnitsUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/accomodation/object/(%OBJECTID%)/unit';
  private addUnitUrl = '';



  constructor(private http: HttpClient) { }

  getObjectCategories(): Observable<any> {
    return this.http.get(this.getObjectCategoriesUrl);
  }

  getObjectType(): Observable<any> {
    return this.http.get(this.getObjectTypesUrl);
  }

  getObject(objectId: string): Observable<any> {
    return this.http.get(this.getObjectsUrl + '/' + objectId);
  }

  getObjects(): Observable<any> {
    return this.http.get(this.getObjectsUrl)
    .pipe(
      retry(1),
      catchError(this.handlerError));
  }

  addObject(): Observable<any> {
    return this.http.get(this.addObjectUrl);
  }

  getUnit(objectId: string, unitId: string): Observable<any> {
    const url: string = this.generateObjectUrl(objectId);
    return this.http.get(url + '/' + unitId);
  }

  getUnits(objectId: string): Observable<any> {
    const url: string = this.generateObjectUrl(objectId);
    return this.http.get(url);
  }

  addUnit(objectId: string, newUnit: SmestajnaJedinica): Observable<any> {
    const url: string = this.generateObjectUrl(objectId);
    return this.http.post(url + '/new', newUnit)
    .pipe(
      retry(1),
      catchError(this.handlerError)
    );
  }

  private generateObjectUrl(objectId: string) {
    const url: string = this.getUnitsUrl.replace('(%OBJECTID%)', objectId);
    return url;
  }

  private handlerError(error: Response) {
    return Observable.throw(error);
  }
}
