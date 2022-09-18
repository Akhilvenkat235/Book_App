import { Component, OnInit } from '@angular/core';
import { User } from '../register/user';
import { UpdateProfileService } from '../update-profile.service';
import { ActivatedRoute } from '@angular/router';
import { LoginService } from '../login.service';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { RouterServiceService } from '../router-service.service';


@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {
  userData:User;
  emailId:string;
  constructor( private profileService: UpdateProfileService
    ,private activatedRoute: ActivatedRoute
    ,private routeService:RouterServiceService
    ,private authService:LoginService) { }

    updateForm=new FormGroup({
      firstName: new FormControl('',[Validators.required]),
      email: new FormControl('',[Validators.required]),
      password: new FormControl('',[Validators.required, Validators.minLength(6)])
    });

  ngOnInit() {
  //  this.getUserDetails();
  }
  //getUserDetails() {
    //console.log("getting user details");
   // this.emailId=this.authService.getUserEmail();

   //this.profileService.userDetailsById(this.emailId)
    //subscribe(data => {
       //        console.log(data);
         //      this.userData=data;
           //   }); 
      //}

      updateUserDetails(){
      this.userData = {userName:this.updateForm.value.firstName,userPassword:this.updateForm.value.password,userEmail:this.updateForm.value.email};
        console.log(this.userData);
       //this.emailId=this.authService.getUserEmail();
        this.profileService.updateUserData(this.userData,this.updateForm.value.email)
            .subscribe(
              data => {
             
           console.log(data);
           console.log('sucess');
           this.routeService.routeToLogin();
                },
                error =>{
                  if(error.status == 200){
                    console.log('sucess');
                    this.routeService.routeToLogin();
                  }else{
                    console.log('unsucess');
                  }
                }
               
            );
    }


}
