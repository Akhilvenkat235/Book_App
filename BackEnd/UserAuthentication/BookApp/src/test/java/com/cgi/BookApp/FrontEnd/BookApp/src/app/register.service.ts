import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  registerURL:string;

  constructor(private httpClient: HttpClient) {
    this.registerURL='http://localhost:8089/api/v1/user';

  }
  register(data):Observable<String>{

   
    return this.httpClient.post<String>(this.registerURL, data, {
      headers : new HttpHeaders().set('content-type', 'application/json')
    });
         
  }
}
