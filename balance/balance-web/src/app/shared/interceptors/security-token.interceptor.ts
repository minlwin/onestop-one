import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, tap } from "rxjs";
import { BalanceAppContext, TOKEN_NAME } from "../services/balance-app.context";

@Injectable()
export class SecurityTokenInterceptor implements HttpInterceptor{

    constructor(private context:BalanceAppContext) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        let newRequest = req

        if(this.context.token) {
            newRequest = req.clone({headers: req.headers.append(TOKEN_NAME, this.context.token)})
        }

        return next.handle(newRequest).pipe(
            tap(event => {
                if(event instanceof HttpResponse) {
                    const resp = event as HttpResponse<any>
                    const token = resp.headers.get(TOKEN_NAME)
                    this.context.setToken(token)
                }
            })
        )
    }

}
