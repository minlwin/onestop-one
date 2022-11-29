import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environments } from 'src/environments/environment';

const API = `${environments.baseApi}/state`

@Injectable({
  providedIn: 'root'
})
export class StateService {

  constructor(private http:HttpClient) { }

  search(form:any) {
    return this.http.get<any[]>(API, {params: form})
  }

  findById(id:any) {
    return this.http.get<any>(`${API}/${id}`)
  }

  save(form:any) {
    const {id, ... param} = form
    return id == 0 ? this.create(param) : this.update(id, param)
  }

  upload(file:any) {
    const formData = new FormData
    formData.append('file', file, file.name)
    return this.http.post<any[]>(`${API}/upload`, formData)
  }

  private create(param:any) {
    return this.http.post<any>(API, param)
  }

  private update(id:any, param:any) {
    return this.http.put<any>(`${API}/${id}`, param)
  }
}
