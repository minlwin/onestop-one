import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AbstractService } from './abstract.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryService extends AbstractService{

  constructor(private http:HttpClient) {
    super()
  }

  getCategories(count?:number):Observable<any[]> {
    let params:any = {limit: count}
    return this.http.get<any[]>(this.getBaseApi('category'), {params: params})
  }

  findById(id: number) {
    return this.http.get<any>(`${this.getBaseApi('category')}/${id}`)
  }
}
