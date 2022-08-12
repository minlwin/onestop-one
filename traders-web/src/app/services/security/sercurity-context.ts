import { ForwardRefHandling } from "@angular/compiler";
import { Injectable } from "@angular/core";

const SECURITY_INFO = "com.jdc.traders.security"
const SECURITY_TOKEN = "com.jdc.traders.token"

@Injectable({providedIn: 'root'})
export class SecurityContext {

  signOut() {
    this.security = undefined
    this.token = undefined
  }

  constructor() {
    let info = localStorage.getItem(SECURITY_INFO)
    if(info) {
      this._security = JSON.parse(info)
    }
    this._token = localStorage.getItem(SECURITY_TOKEN)
  }

  private _security?:SecurityInfo
  private _token:string | undefined | null

  set security(data:SecurityInfo | undefined) {
    this._security = data

    if(data) {
      localStorage.setItem(SECURITY_INFO, JSON.stringify(data))
    } else {
      localStorage.removeItem(SECURITY_INFO)
    }
  }

  set token(data:string | undefined| null) {
    this._token = data
    if(data) {
      localStorage.setItem(SECURITY_TOKEN, data)
    } else {
      localStorage.removeItem(SECURITY_TOKEN)
    }
  }

  get security() {
    return this._security;
  }

  get token() {
    return this._token
  }
}

export interface SecurityInfo {
  id:number
  name:string
  role:'Admin' | 'Member'
  email:string
}
