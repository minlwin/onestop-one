import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environments } from 'src/environments/environment';

const API = `${environments.baseApi}/district`

@Injectable({
  providedIn: 'root'
})
export class DistrictService {

  constructor(private http:HttpClient) { }

  search(form: any) {
    return this.http.get<any[]>(API, {params: form})
  }

  findById(id:any) {
    return this.http.get<any>(`${API}/${id}`)
  }

  save(form:any) {
    const {id, ...params} = form
    return id == 0 ? this.create(params) : this.update(id, params)
  }

  upload(state:number, file:any) {
    const formData = new FormData
    formData.append('file', file, file.name)
    return this.http.post<any>(`${API}/upload/${state}`, formData)
  }

  private create(params:any) {
    return this.http.post<any>(API, params)
  }

  private update(id:number, params:any) {
    return this.http.put<any>(`${API}/${id}`, params)
  }
}
