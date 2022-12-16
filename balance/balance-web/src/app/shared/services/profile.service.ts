import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ApiResult, BASE_API } from ".";

const API = `${BASE_API}/member`

@Injectable({providedIn: 'root'})
export class ProfileService {

  constructor(private http:HttpClient) {}

  updateProfile(form:any) {
    return this.http.put<ApiResult>(`${API}/profile`, form)
  }

  changePassword(form:any) {
    return this.http.put<ApiResult>(`${API}/password`, form)
  }
}
