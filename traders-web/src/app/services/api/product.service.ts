import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AbstractService } from './abstract.service';

@Injectable({providedIn: 'root'})
export class ProductSerivce extends AbstractService {

  constructor(private http:HttpClient) {
    super()
  }

  findById(id:number) {
    return this.http.get<any[]>(`this.getBaseApi('product')/${id}`)
  }

  search(form:any) {
    return this.http.get<any[]>(this.getBaseApi('product'), {params: form})
  }
}
