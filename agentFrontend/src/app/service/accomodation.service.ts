import { SmestajnaJedinica } from 'src/app/model/smestaj/smestajna-jedinica.model';
import { AppConfigService } from './app-config.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { SmestajniObjekat } from '../model/smestaj/smestajni-objekat.model';

@Injectable({
  providedIn: 'root'
})
export class AccomodationService {

  private getObjectCategoriesUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort +
  '/agent/accomodation/object/categories';
  private getObjectTypesUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/accomodation/object/types';
  private getExtrasUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/accomodation/object/extras';

  private getObjectBaseUrl = '';
  private getObjectsUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/accomodation/object';
  private addObjectUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/accomodation/object/new';

  private getUnitBaseUrl = '';
  private getUnitsUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/accomodation/object/(%OBJECTID%)/unit';
  private addUnitUrl = '';



  constructor(private http: HttpClient) { }

  getObjectCategories(): Observable<any> {
    return this.http.get(this.getObjectCategoriesUrl);
  }

  getObjectTypes(): Observable<any> {
    return this.http.get(this.getObjectTypesUrl);
  }

  getExtras(): Observable<any> {
    return this.http.get(this.getExtrasUrl);
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

  addObject(newObject: SmestajniObjekat): Observable<any> {
    return this.http.post(this.addObjectUrl, newObject)
    .pipe(
      retry(1),
      catchError(this.handlerError)
    );
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
