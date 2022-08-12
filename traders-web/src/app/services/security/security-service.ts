import { tap } from 'rxjs';
import { SecurityContext } from './sercurity-context';
import { HttpClient } from '@angular/common/http';
import { AbstractService } from './../api/abstract.service';
import { Injectable } from "@angular/core";

@Injectable({providedIn: 'root'})
export class SecurityService extends AbstractService{

  constructor(private http:HttpClient, private context:SecurityContext) {
    super('security')
  }

  signIn(form:any) {
    return this.http.post<any>(`${this.baseApi}/signin`, form).pipe(
      tap(result => {
        if(result.success) {
          this.context.security = result.loginUser
        }
      })
    )
  }

  signUp(form:any) {
    return this.http.post<any>(`${this.baseApi}/signup`, form).pipe(
      tap(result => {
        if(result.success) {
          this.context.security = result.loginUser
        }
      })
    )
  }
}
