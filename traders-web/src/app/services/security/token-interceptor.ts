import { SecurityContext } from './sercurity-context';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, tap } from "rxjs";

const TOKEN_NAME = "traders-token"

@Injectable()
export class TokenInterceptor implements HttpInterceptor{

  constructor(private context:SecurityContext) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let cloneRequest = req

    if(this.context.token) {
      cloneRequest = req.clone({headers: req.headers.append(TOKEN_NAME, this.context.token)})
    }

    return next.handle(cloneRequest).pipe(
      tap(req => {
        if(req instanceof HttpResponse) {
          this.context.token = req.headers.get(TOKEN_NAME)
        }
      })
    )
  }

}
