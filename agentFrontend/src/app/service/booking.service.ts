import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfigService } from './app-config.service';
import { Observable } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { RezervacijaKorisnika } from '../model/rezervacija/rezervacija-korisnika.model';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  private getBookingsUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/booking/all';
  private getBookingUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/booking';

  private getBookingsByUnitUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/booking/all/unit';
  private getBookingsByObjectUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/booking/all/object';


  private getBookingsHistoryUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/booking/all';
  private getBookingsUpcomingUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/booking/all';

  private confirmBookingUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort +
  '/agent/booking/confirmation/bookingId';
  private makeBookingUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/booking/new';


  constructor(private http: HttpClient) { }

  getAllBookings(): Observable<any> {
    return this.http.get(this.getBookingsUrl)
    .pipe(
      retry(1),
      catchError(this.handlerError));
  }

  getBooking(bookingId: string): Observable<any> {
    return this.http.get(this.getBookingUrl + '/' + bookingId);
  }

  getAllBookingsByUnit(unitId: string): Observable<any> {
    return this.http.get(this.getBookingsByUnitUrl + '/' + unitId)
    .pipe(
      retry(1),
      catchError(this.handlerError));
  }

  getAllBookingsByObject(unitId: string): Observable<any> {
    return this.http.get(this.getBookingsByObjectUrl + '/' + unitId)
    .pipe(
      retry(1),
      catchError(this.handlerError));
  }

  getAllBookingsHistory(): Observable<any> {
    return this.http.get(this.getBookingsHistoryUrl)
    .pipe(
      retry(1),
      catchError(this.handlerError));
  }

  getAllBookingsUpcoming(): Observable<any> {
    return this.http.get(this.getBookingsUpcomingUrl)
    .pipe(
      retry(1),
      catchError(this.handlerError));
  }

  makeBooking(booking: RezervacijaKorisnika): Observable<any> {
    return this.http.post(this.makeBookingUrl, booking)
    .pipe(
      retry(1),
      catchError(this.handlerError));
  }

  confirmBooking(bookingId: string): Observable<any> {
    return this.http.get(this.confirmBookingUrl + '/' + bookingId)
    .pipe(
      retry(1),
      catchError(this.handlerError));
  }

  // private generateObjectUrl(objectId: string) {
  //   const url: string = this.getUnitsUrl.replace('(%OBJECTID%)', objectId);
  //   return url;
  // }

  private handlerError(error: Response) {
    return Observable.throw(error);
  }
}
