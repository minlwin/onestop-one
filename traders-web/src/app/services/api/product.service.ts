import { TopSellersService } from './top-sellers.service';
import { CategoryService } from './category.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { mergeMap, toArray, map } from 'rxjs';
import { AbstractService } from './abstract.service';

@Injectable({providedIn: 'root'})
export class ProductSerivce extends AbstractService {

  constructor(
    private http:HttpClient,
    private categoryService:CategoryService,
    private sellerService:TopSellersService
    ) {
    super()
  }

  findById(id:number) {
    return this.http.get<any[]>(this.getBaseApi('product'))
      .pipe(
        map(array => array.filter(item => item.id == id).pop()),
        mergeMap(item => this.categoryService.findById(item.category).pipe(
            map(category => {
              item.category = category
              return item
            }))
        ),
        mergeMap(item => this.sellerService.findById(item.seller).pipe(
          map(seller => {
            item.seller = seller
            return item
          })
        ))
      )
  }

  search(form:any) {
    return this.http.get<any[]>(this.getBaseApi('product'))
      .pipe(
        mergeMap(array => array),
        mergeMap(item =>
          this.categoryService.findById(item.category)
            .pipe(
              map(category => {
                item.category = category
                return item
              })
            )),
        mergeMap(item =>
          this.sellerService.findById(item.seller)
            .pipe(map(seller => {
              item.seller = seller
              return item
            }))
        ),
        toArray()
      )
  }
}
