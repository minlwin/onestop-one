import { ProductSerivce } from './../../../services/api/product.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { CategoryService } from '../../../services/api/category.service';
import { TopSellersService } from '../../../services/api/top-sellers.service';

@Component({
  templateUrl: './products.component.html',
  styles: [
  ]
})
export class ProductsComponent implements OnInit {

  form:FormGroup
  title = ''
  list:any[] = []

  constructor(
    builder:FormBuilder,
    route:ActivatedRoute,
    categoryService:CategoryService,
    sellerService:TopSellersService,
    private productService:ProductSerivce) {

    this.form = builder.group({
      category: 0,
      seller: 0,
      keyword: ''
    })

    route.params.subscribe(param => {
      if(param['category']) {
        this.form.patchValue({category : param['category']})
        categoryService.findById(param['category'])
          .subscribe(data => this.title = data.name)
      }

      if(param['seller']) {
        this.form.patchValue({seller : param['seller']})
        sellerService.findById(param['seller'])
          .subscribe(data => this.title = data.name)
      }

      this.search()
    })
  }

  ngOnInit(): void {
  }

  search() {
    this.productService.search(this.form.value)
      .subscribe(result => this.list = result)
  }

  clear() {
    this.form.patchValue({
      category: 0,
      seller: 0,
      keyword: ''
    })

    this.list = []

    this.search()
  }

}
