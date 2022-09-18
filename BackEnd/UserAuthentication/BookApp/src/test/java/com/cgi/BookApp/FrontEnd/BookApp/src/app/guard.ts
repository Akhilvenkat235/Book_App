import { Injectable} from '@angular/core';

import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { LoginService } from './login.service';


@Injectable()
export class Gaurd implements CanActivate {

  constructor(private authService:LoginService,private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot):boolean {
        const temp = !this.authService.getBearerToken();
        if(temp){
            return true;
        }
        else{
            this.router.navigate(['/', 'login']);
            alert("Login first")
            return false;
        }
    }
}