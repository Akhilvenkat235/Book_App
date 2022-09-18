import { Component, OnInit } from '@angular/core';
import {  Validators, FormControl } from '@angular/forms';
import { LoginService } from '../login.service';
import { User } from '../register/user';
import { RouterServiceService } from '../router-service.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent  {


  email = new FormControl('', [Validators.required, Validators.email]);
  getErrorMessage() {
    return this.email.hasError('required') ? 'You must enter a value' :
        this.email.hasError('email') ? 'Not a valid email' :
            '';
        
  }

  user: User;
  userEmail: string;
  userPassword: string;
  public bearerToken: any;
  public submitMessage: string;
  passData:string;
  constructor(private authService: LoginService,
              private routerService: RouterServiceService) {
      this.user = new User();
    }
  signIn() {
    console.log (' calling login method ');
    this.user.userEmail = this.userEmail;
    this.user.userPassword = this.userPassword;
    this.authService.authenticateUser(this.user).subscribe(
        data =>  {
          
        alert('Successfully loggedin')
        this.authService.setBearerToken(data['token'])
        this.authService.setUserEmail(this.userEmail);
      this.routerService.routeToDashboard();   
          },
          err => {
            if (err.status === 200) {
              this.routerService.routeToDashboard();   

              //alert('emailId and password mismatch');
             // this.routerService.routeToFavourites();
            } else {
              alert('user does not exist');
            }
        });
      }
      getInfo(){
        return this.passData;
      }
  // userData: User;
  // submitted = false;

  // emailFormControl = new FormControl('', [
  //   Validators.required,
  //   Validators.email,
  // ]);

  // signForm = this.fb.group({
  //   userEmail: ['', [Validators.required, Validators.email]],
  //   userPassword: ['', [Validators.required, Validators.minLength(6)]]
  // });
  // submitMessage: any;
  // userPassword: string;
  // userEmail: string;

  // constructor(private fb: FormBuilder, private route: Router,private loginService: LoginService) { }

  // ngOnInit() {

  // }

  // get f() {
  //   return this.signForm.controls;
  // }

  // signIn() {
  //   console.log (' calling login method ');
  //   this.userData.userEmail = this.userEmail;
  //   this.userData.userPassword = this.userPassword;
  //     this.loginService.authenticateUser(this.userData).subscribe(
  //       data => {
  //         alert('sucessfully loggedin');
  //         this.route.navigate(['/', 'dashboard']);
  //       },
  //       err => {
  //         if (err.status === 401) {
  //           alert(err.error);
  //         } else {
  //           this.submitMessage = err.error.message;
  //         }
  //       });

  //     console.log(this.signForm.value);
  //   }
  
      
    }


  


