import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private authService:LoginService) {}
  loggedInUserId:boolean;
  userId:string;

  ngOnInit() {
  }
checkUser(){
  if(this.authService.getBearerToken()==null){
    return false
  }
  return true;
}
}
