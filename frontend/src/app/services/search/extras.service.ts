import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { EXTRAS_API } from './../../globals';
import { Injectable } from '@angular/core'; 

@Injectable({
  providedIn: 'root'
})
export class ExtrasService {

  constructor(private http: HttpClient) { 
    
  }

  getAll(): Observable<any> {
    return this.http.get(EXTRAS_API);
  }

}
