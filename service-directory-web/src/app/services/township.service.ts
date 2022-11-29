import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environments } from 'src/environments/environment';

const API = `${environments.baseApi}/township`

@Injectable({
  providedIn: 'root'
})
export class TownshipService {

  constructor(private http:HttpClient) { }

  search(form:any) {
    return this.http.get<any[]>(API, {params: form})
  }

  findById(id:any) {
    return this.http.get<any>(`${API}/${id}`)
  }

  save(form:any) {
    const {id, ... params} = form
    return id > 0 ? this.update(id, params) : this.create(form)
  }

  upload(district:number, file:any) {
    const formData = new FormData
    formData.append('file', file, file.name)
    return this.http.post<any[]>(`${API}/upload/${district}`, formData)
  }

  private create(form:any) {
    return this.http.post<any>(API, form)
  }

  private update(id:number, form:any) {
    return this.http.put(`${API}/${id}`, form)
  }
}
