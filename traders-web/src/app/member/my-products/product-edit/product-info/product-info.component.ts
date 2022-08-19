import { map } from 'rxjs';
import { CategoryService } from './../../../../services/api/category.service';
import { ProductEditState } from './../product-edit.state';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product-info',
  templateUrl: './product-info.component.html',
  styles: [
  ]
})
export class ProductInfoComponent implements OnInit {

  categoryNames:string[] = []

  constructor(public state:ProductEditState, private catService:CategoryService) {
    this.state.view = "info"
  }

  ngOnInit(): void {
    this.catService.getAll().pipe(
      map(array => array.map(category => category.name))
    ).subscribe(result => this.categoryNames = result)
  }

}
