import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AbstractService } from './abstract.service';

export enum Status {
  Order, Shipped, Finish, Cancel
}

@Injectable({providedIn: 'root'})
export  class SaleService extends AbstractService {

  constructor(private http:HttpClient) {
    super('sale')
  }

  search(form:any) {
    return this.http.get<any[]>(this.baseApi, {params: form})
  }

  findById(id:number) {
    return this.http.get<any>(`${this.baseApi}/${id}`)
  }

  order(form:any) {
    return this.http.post<any>(this.baseApi, form)
  }
}
