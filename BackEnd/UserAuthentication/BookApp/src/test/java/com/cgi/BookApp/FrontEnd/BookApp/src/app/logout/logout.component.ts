import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { RouterServiceService } from '../router-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {
  loginService: any;

  constructor(private authService:LoginService, router:RouterServiceService) { }

  ngOnInit() {
    this.authService.logOutUser();
  }

}
