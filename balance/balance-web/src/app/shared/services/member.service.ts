import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ApiResult, BASE_API } from ".";

const API = `${BASE_API}/account`

@Injectable({providedIn: 'root'})
export class MemberService {

    constructor(private http:HttpClient) {}

    search(form:any) {
        return this.http.get<ApiResult>(API, {params: form})
    }

    findById(id:number) {
        return this.http.get<ApiResult>(`${API}/${id}`)
    }

    updateRole(id:number, role:string) {
        return this.http.put<ApiResult>(`${API}/${id}`, {value: role})
    }

    updateStatus(id:number, status:boolean) {
        return this.http.put<ApiResult>(`${API}/${id}`, {value: status})
    }
}
