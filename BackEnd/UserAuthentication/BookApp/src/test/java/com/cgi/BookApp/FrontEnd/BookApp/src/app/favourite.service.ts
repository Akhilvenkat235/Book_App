import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

//import { HttpHeaders, HttpClient } from '../../node_modules/@angular/common/http';
import { Favourite } from './favourite';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class FavouriteService {

headers : HttpHeaders;

  constructor(private http: HttpClient , private loginService:LoginService
   ,private authService: LoginService ) {

      let token="Bearer "+this.loginService.getBearerToken();
      this.headers=new HttpHeaders()
      .set('Content-Type', 'application/json; charset=utf-8').set('Authorization',token);;
   
    }
  
  addFavourite(favourite: Favourite): Observable<String>{
     let token="Bearer"+this.loginService.getBearerToken();
      console.log("Favourite added service ");
 
      let headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8').set('Authorization',token);
    return this.http.post<String>("http://localhost:8089/api/v1/favourite",favourite,
    {headers:headers});
  }

  deleteFavourite(userEmail:string,isbn:string){
    let token="Bearer "+this.loginService.getBearerToken();
      console.log("Favourite deleted");
     let headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8').set('Authorization',token);
      return this.http.delete<Favourite>("http://localhost:8089/api/v1/favourite/"+userEmail+"/"+isbn,{headers : headers});
  }
  
  getAllFavourites(userEmail:String):Observable<Favourite[]>{
      console.log("all favourites");
      userEmail=  this.authService.getUserEmail();
      return this.http.get<Favourite[]>("http://localhost:8089/api/v1/favourite/"+userEmail,{headers : this.headers});
  }
}
