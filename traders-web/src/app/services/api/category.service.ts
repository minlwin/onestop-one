import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { AbstractService } from './abstract.service';

@Injectable({
  providedIn: 'root'
})
export class CategoryService extends AbstractService{

  constructor(private http:HttpClient) {
    super()
  }

  getCategories(count?:number):Observable<any[]> {
    return this.http.get<any[]>(this.getBaseApi('category'))
      .pipe(
        map(array => array.sort((a, b) => b.products - a.products)),
        map(array => count ? array.slice(0, count) : array)
      )
  }

  findById(id:number) {
    return this.http.get<any[]>(this.getBaseApi('category'))
      .pipe(
        map(array => array.filter(item => item.id == id).pop())
      )
  }
}
