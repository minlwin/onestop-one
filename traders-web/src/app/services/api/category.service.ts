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

  getCategories(count?:number):Observable<any[]> {
    let params:any = count ? {limit: count} : undefined
    return this.http.get<any[]>(this.baseApi, {params: params})
  }

  findById(id: number) {
    return this.http.get<any>(`${this.baseApi}/${id}`)
  }

  getSellerCategory(sellerId:number) {
    return this.http.get<any[]>(`${this.baseApi}/seller/${sellerId}`)
  }
}
