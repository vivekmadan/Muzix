import { Injectable } from '@angular/core';
import { CanActivate } from '@angular/router/src/interfaces';
import { AuthenticationserviceService } from '../authentication/authenticationservice.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(
    private authService: AuthenticationserviceService,
    private route: Router
  ) { }
  
  canActivate(){
    if(this.authService.isTokenExpired()){
      console.log('In CanActivate');
      return true;
    }

    this.route.navigate(['/login']);
    return false;
  }
}
