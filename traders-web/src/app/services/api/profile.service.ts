import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AbstractService } from './abstract.service';

@Injectable({providedIn: 'root'})
export class ProfileService extends AbstractService {

  constructor(private http:HttpClient) {
    super('profile')
  }

  findById(id:number) {
    return this.http.get<any>(`${this.baseApi}/${id}`)
  }

  save(form:any) {
    return this.http.put<any>(this.baseApi, form)
  }
}
