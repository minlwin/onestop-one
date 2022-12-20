import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ApiResult, BASE_API } from ".";

const API = `${BASE_API}/balance`

@Injectable({providedIn: 'root'})
export class BalanceService {

  constructor(private http:HttpClient) {}

  search(form:any) {
    return this.http.get<ApiResult>(API, {params: form})
  }

  findById(id:number) {
    return this.http.get<ApiResult>(`${API}/${id}`)
  }

  save(form:any) {
    const {id, ... dto} = form
    return id == 0 ? this.create(dto) : this.update(id, dto)
  }

  private create(dto:any) {
    return this.http.post<ApiResult>(API, dto)
  }

  private update(id:number, dto:any) {
    return this.http.put<ApiResult>(`${API}/${id}`, dto)
  }

  updateFixed(id: number, status:boolean) {
    return this.http.put<ApiResult>(`${API}/${id}/fixed`, {value : status})
  }

  delete(id:number, status:boolean) {
    return this.http.put<ApiResult>(`${API}/${id}/deleted`, {value : status})
  }

  deleteDetails(id: number) {
    return this.http.delete<ApiResult>(`${API}/${id}`)
  }

}
