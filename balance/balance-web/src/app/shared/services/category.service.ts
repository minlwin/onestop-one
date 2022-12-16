import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ApiResult, BASE_API } from ".";

const API = `${BASE_API}/category`

@Injectable({providedIn: 'root'})
export class CategoryService {

  constructor(private http:HttpClient) {}

  search(form:any) {
    return this.http.get<ApiResult>(API, {params: form})
  }

  findById(id:number) {
    return this.http.get<ApiResult>(`${API}/${id}`)
  }

  save(form:any) {
    const {id, ... dto} = form
    return id == 0? this.create(dto) : this.update(id, dto)
  }

  upload(file:any) {
    const form = new FormData
    form.append('file', file, file.name)
    return this.http.post<ApiResult>(`${API}/upload`, form)
  }

  private create(dto:any) {
    return this.http.post<ApiResult>(API, dto)
  }

  private update(id:number, dto:any) {
    return this.http.put<ApiResult>(`${API}/${id}`, dto)
  }
}
