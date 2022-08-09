import { Injectable } from "@angular/core";

const SECURITY_INFO = "com.jdc.traders.security"

@Injectable({providedIn: 'root'})
export class SecurityContext {

  signOut() {
    this.security = undefined
  }

  constructor() {
    let info = localStorage.getItem(SECURITY_INFO)
    if(info) {
      this._security = JSON.parse(info)
    }
  }

  private _security?:SecurityInfo

  set security(data:SecurityInfo | undefined) {
    this._security = data

    if(data) {
      localStorage.setItem(SECURITY_INFO, JSON.stringify(data))
    } else {
      localStorage.removeItem(SECURITY_INFO)
    }
  }

  get security() {
    return this._security;
  }
}

export interface SecurityInfo {
  name:string
  role:'Admin' | 'Member'
  email:string
}
