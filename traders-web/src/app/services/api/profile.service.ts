import { SecurityContext } from './../security/sercurity-context';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AbstractService } from './abstract.service';

@Injectable({providedIn: 'root'})
export class ProfileService extends AbstractService {

  constructor(private http:HttpClient, private context:SecurityContext) {
    super('profile')
  }

  findById(id:number) {
    return this.http.get<any>(`${this.baseApi}/${id}`)
  }

  save(form:any) {
    return this.http.put<any>(this.baseApi, form)
  }

  uploadImage(image:File) {
    const form = new FormData()
    form.append('file', image, image.name)
    return this.http.put<any>(`${this.baseApi}/image/${this.context.security?.id}`, form)
  }

  addAddress(id:number, form:any) {
    return this.http.post<any>(`${this.baseApi}/address/${id}`, form)
  }

  findAddressById(id:number) {
    return this.http.get<any>(`${this.baseApi}/address/${id}`)
  }

  addBanking(id:number, form:any) {
    return this.http.post<any>(`${this.baseApi}/banking/${id}`, form)
  }
}
