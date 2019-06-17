import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


//dlet url = string = "//localhost:8080/api/test";

@Injectable({
  providedIn: 'root'
})
export class TestService {

  constructor(private http: HttpClient) { }

  test() {
    alert("Service!")
    return this.http.get("//localhost:8111/api/test/proba");
  }

}
