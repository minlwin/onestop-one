import { map } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AbstractService } from './abstract.service';

@Injectable({
  providedIn: 'root'
})
export class TopSellersService extends AbstractService{

  constructor(private http:HttpClient) {
    super()
  }

  getTopSellers() {
    return this.http.get<any[]>(this.getBaseApi('sellers'))
      .pipe(
        map(array => array.sort((a, b) => b.sales - a.sales))
      )
  }

  findById(id:number) {
    return this.http.get<any[]>(this.getBaseApi('sellers'))
      .pipe(
        map(array => array.filter(item => item.id == id).pop())
      )
  }
}
