import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AbstractService } from './abstract.service';

@Injectable({providedIn: 'root'})
export class ProductSerivce extends AbstractService {

  constructor(private http:HttpClient) {
    super('product')
  }

  findById(id:number) {
    return this.http.get<any>(`${this.baseApi}/${id}`)
  }

  search(form:any) {
    return this.http.get<any[]>(this.baseApi, {params: form})
  }

  save(form:any) {
    if(form.id == 0) {
      return this.http.post<any>(this.baseApi, form)
    }
    return this.http.put<any>(this.baseApi, form)
  }

  uploadPhoto(productId:number, files:FileList) {
    const form = new FormData()
    for(let i = 0; i < files.length; i ++) {
      form.append("files", files[i], files[i].name)
    }
    return this.http.put<any>(`${this.baseApi}/${productId}/photos`, form)
  }
}
