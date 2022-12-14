import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ApiResult, BASE_API } from ".";

const API = `${BASE_API}/security`

@Injectable({providedIn: 'root'})
export class SecurityService {

    constructor(private http:HttpClient) {}

    signIn(form:any) {
        return this.http.post<ApiResult>(`${API}/signin`, form)
    }

    signUp(form:any) {
        return this.http.post<ApiResult>(`${API}/signup`, form)
    }
}
