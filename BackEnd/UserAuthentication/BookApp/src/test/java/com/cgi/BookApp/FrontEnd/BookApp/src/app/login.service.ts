import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RouterServiceService } from './router-service.service';


@Injectable({
 providedIn: 'root'
})

export class LoginService {
  loginURL: string;
  headers: HttpHeaders;
  

constructor(private httpClient: HttpClient,private router:RouterServiceService,private authService:LoginService) {
  
  this.loginURL='http://localhost:8088/api/v1/auth/login'
}


 
authenticateUser(data): Observable <string> {
  return this.httpClient.post<string>('http://localhost:8088/api/v1/auth/login', data, {
    headers : new HttpHeaders().set('content-type', 'application/json')
  });
}

// loginUser(data) {
//       return this.httpClient.post(this.loginURL, data, {
//         headers : new HttpHeaders().set('content-type', 'application/json')
//       });
//     }

setBearerToken(token) {
   sessionStorage.setItem('authToken', token);
}

getBearerToken(): string {
  console.log('getting bear token ' + sessionStorage.getItem('authToken'));
   return sessionStorage.getItem('authToken');
}

setUserEmail(userEmail) {
  localStorage.setItem('emailId', userEmail);
}

getUserEmail(): string {

  return localStorage.getItem('emailId');
}

logOutUser():Observable<String>{
  sessionStorage.removeItem('authToken');
  let token = "Bearer"+this.authService.getBearerToken();
  let headers = new HttpHeaders().set('Authorization',token);
  if(token==null)
  {
    this.router.routeToLogin();
  }
  
  return  this.httpClient.get<String>("http://localhost:8088/logout"+{headers : this.headers});
  
}


isUserLoggedIn(userEmail: string):  boolean {
  return (userEmail === this.getUserEmail());
}

 }
