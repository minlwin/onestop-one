import { Injectable } from "@angular/core"

const LOGIN_USER_KEY = "com.jdc.balance.user"
const TOKEN_KEY = "com.jdc.balance.token"

export const TOKEN_NAME = "balance-token"

@Injectable({providedIn: 'root'})
export class BalanceAppContext {
    private _loginUser:any
    private _token:string | undefined | null

    constructor() {
        const storedUser = localStorage.getItem(LOGIN_USER_KEY)
        if(storedUser) {
            this._loginUser = JSON.parse(storedUser)
        }

        this._token = localStorage.getItem(TOKEN_KEY)
    }

    setLoginUser(user:any) {
        if(user) {
            this._loginUser = user;
            localStorage.setItem(LOGIN_USER_KEY, JSON.stringify(user))
        } else {
            this.logout()
        }
    }

    setToken(token:string | null) {
        if(token) {
            this._token = token
            localStorage.setItem(TOKEN_KEY, token)
        } else {
            this.logout()
        }
    }

    logout() {
        this._loginUser = undefined
        this._token = undefined
        localStorage.clear()
    }

    get token() {
        return this._token;
    }

    get loginUser() {
        return this._loginUser;
    }
}
