import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AbstractService } from './abstract.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryService extends AbstractService{

  constructor(private http:HttpClient) {
    super('category')
  }

  getAll() {
    return this.http.get<any[]>(`${this.baseApi}`)
  }

  findById(id: number) {
    return this.http.get<any>(`${this.baseApi}/${id}`)
  }

  getCategories(count?:number):Observable<any[]> {
    let params:any = count ? {limit: count} : undefined
    return this.http.get<any[]>(`${this.baseApi}/top`, {params: params})
  }

  getSellerCategory(sellerId:number) {
    return this.http.get<any[]>(`${this.baseApi}/seller/${sellerId}`)
  }

}
