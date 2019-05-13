import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { AuthenticationserviceService } from '../authentication/authenticationservice.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  constructor(private authService: AuthenticationserviceService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{
    console.log('In Intercept');
    request = request.clone({
      setHeaders: {
        Authorization: `Bearer ${this.authService.getToken()}` 
      }
    });

    return next.handle(request);
  }
}
