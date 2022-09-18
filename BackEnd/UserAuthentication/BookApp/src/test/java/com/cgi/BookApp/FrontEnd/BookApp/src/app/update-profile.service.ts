import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './register/user';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class UpdateProfileService {

  
  constructor(private httpClient: HttpClient,private authService:LoginService) { }
 // userDetailsById(userEmail: string):Observable<User> {
   // console.log("user details by id in service")
    //return this.httpClient.get<User>('http://localhost:8089/user/'+ userEmail, {
      //headers : new HttpHeaders().set('content-type', 'application/json')
    //});
 // }
  updateUserData(user: User,userEmail:string):Observable<User>  {
    const temp = this.authService.getBearerToken()
    // return this.http.post(ConstantVariables.BASE_API_URL+'/update-user-data', user, header.head()).map((response: Response) => response.json());
    return this.httpClient.put<User>('http://localhost:8089/user/'+ userEmail, user,{
      headers : new HttpHeaders().set('content-type', 'application/json')
    });
  }

}
