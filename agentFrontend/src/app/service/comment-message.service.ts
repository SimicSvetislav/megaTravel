import { Poruka } from 'src/app/model/rezervacija/poruka.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfigService } from './app-config.service';
import { Observable } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CommentMessageService {

  constructor(private http: HttpClient) { }

  private getAllSentMessagesUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/feedback/message/sent';
  private getAllReceivedMessagesUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/feedback/message/inbox';

  private getMessageUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/feedback/message';
  private answerMessageUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/feedback/message/answer';

  private getCommentsByUnitUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort + '/agent/feedback/comments/unitId';
  private  getCommentsByObjectUrl = 'http://localhost:' + AppConfigService.settings.backend.serverPort +
    '/agent/feedback/comments/objectId';

  getAllSentMessages(): Observable<any> {
    return this.http.get(this.getAllSentMessagesUrl)
    .pipe(
      retry(1),
      catchError(this.handlerError));
  }

  getAllReceivedMessages(): Observable<any> {
    return this.http.get(this.getAllReceivedMessagesUrl)
    .pipe(
      retry(1),
      catchError(this.handlerError));
  }

  getMessage(messageId: string): Observable<any> {
    return this.http.get(this.getMessageUrl + '/' + messageId)
    .pipe(
      retry(1),
      catchError(this.handlerError));
  }

  answerMessage(message: Poruka): Observable<any> {
    return this.http.post(this.answerMessageUrl, message)
    .pipe(
      retry(1),
      catchError(this.handlerError));
  }

  getCommentsByUnit(unitId: string): Observable<any> {
    return this.http.get(this.getCommentsByUnitUrl + '/' + unitId)
    .pipe(
      retry(1),
      catchError(this.handlerError));
  }

  getCommentsByObject(objectId: string): Observable<any> {
    return this.http.get(this.getCommentsByObjectUrl + '/' + objectId)
    .pipe(
      retry(1),
      catchError(this.handlerError));
  }

  private handlerError(error: Response) {
    return Observable.throw(error);
  }

}
