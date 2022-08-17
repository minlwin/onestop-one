import { Router } from '@angular/router';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable } from 'rxjs';
import { SecurityContext } from './sercurity-context';

@Injectable()
export class LogoutInterceptor implements HttpInterceptor {

  constructor(private router:Router, private context:SecurityContext) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError(error => {

        if(error.status == 401 || error.status == 403) {
          this.context.signOut()
          this.router.navigate(['/public', 'signin'])
        }

        throw(error)
      })
    )
  }

}
