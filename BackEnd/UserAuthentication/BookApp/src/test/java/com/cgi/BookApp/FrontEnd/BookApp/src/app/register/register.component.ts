import { Component, OnInit } from '@angular/core';
import { FormControl ,FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import{User} from './user'
import { RegisterService } from '../register.service';
import { first } from 'rxjs/operators';
import { RouterServiceService } from '../router-service.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
 userName:string;
 userPassword:string;
 userEmail:string;
 user:User[];
//name=new FormControl();
registrationForm=new FormGroup({
  firstName: new FormControl('',[Validators.required]),
  email: new FormControl('',[Validators.required]),
  password: new FormControl('',[Validators.required, Validators.minLength(6)])
});
  constructor(private registerService: RegisterService,private routeService:RouterServiceService)
    { }

  ngOnInit() {
   
   
    }

  
     
  onSubmit()
  {
  
   const user:any = {userPassword: this.registrationForm.value.password,userName:this.registrationForm.value.firstName,userEmail:this.registrationForm.value.email};
   this.registerService.register(user)
       .pipe(first())
       .subscribe(
           data => {
               // this.alertService.success('Registration successful', true);
               this.routeService.routeToLogin();
           },
           error => {
             if(error.status == 201){
               console.log('sucess');
               alert('Registered successfully');
              
             }else{
               console.log('unsucess');
               alert('Not registred');
             }
  
           });
}


}
