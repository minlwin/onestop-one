import { SecurityContext, SecurityInfo } from './sercurity-context';
import { Injectable } from "@angular/core";

@Injectable({providedIn: 'root'})
export class SecurityService {

  constructor(private context:SecurityContext) {}

  signIn(form:any):SecurityInfo {

    this.context.security = {
      name: form.password,
      role: 'Member',
      email: form.email
    }

    return this.context.security
  }
}
