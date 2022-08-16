import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AbstractService } from './abstract.service';

const API = 'seller/top'

@Injectable({
  providedIn: 'any'
})
export class TopSellersService extends AbstractService{

  constructor(private http:HttpClient) {
    super(API)
  }

  getTopSellers() {
    let params = {limit: 10}
    return this.http.get<any[]>(this.baseApi, {params: params})
  }

  findById(id: number) {
    return this.http.get<any>(`${this.baseApi}/${id}`)
  }

}
