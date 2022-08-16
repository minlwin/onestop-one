import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AbstractService } from './abstract.service';

@Injectable({providedIn: 'any'})
export class ProductSerivce extends AbstractService {

  constructor(private http:HttpClient) {
    super('product')
  }

  findById(id:number) {
    return this.http.get<any[]>(`${this.baseApi}/${id}`)
  }

  search(form:any) {
    return this.http.get<any[]>(this.baseApi, {params: form})
  }
}
