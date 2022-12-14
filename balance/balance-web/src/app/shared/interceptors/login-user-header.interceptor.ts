import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { BalanceAppContext } from '../services/balance-app.context';

@Injectable()
export class LoginUserHeaderInterceptor implements HttpInterceptor {

  constructor(private context:BalanceAppContext) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    let newRequest = request

    if(this.context.loginUser) {
      newRequest = request.clone({headers: request.headers.append('balance-user', this.context.loginUser.email)})
    }

    return next.handle(newRequest);
  }
}
