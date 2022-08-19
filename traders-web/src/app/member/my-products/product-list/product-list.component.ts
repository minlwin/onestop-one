import { FormGroup, FormBuilder } from '@angular/forms';
import { CategoryService } from './../../../services/api/category.service';
import { SecurityContext } from './../../../services/security/sercurity-context';
import { ProductSerivce } from './../../../services/api/product.service';
import { Component, OnInit } from '@angular/core';

@Component({
  templateUrl: './product-list.component.html',
  styles: [
  ]
})
export class ProductListComponent implements OnInit {

  list:any[] = []
  categories:any[] = []

  form:FormGroup

  constructor(
    builder:FormBuilder,
    private service:ProductSerivce,
    private categoryService:CategoryService,
    private context:SecurityContext) {
      this.form = builder.group({
        category: '',
        keyword: '',
        seller: context.security?.id
      })
    }

  ngOnInit(): void {
    this.categoryService.getSellerCategory(this.context.security?.id!)
      .subscribe(result => this.categories = result)
  }

  search() {
    this.service.search(this.form.value).subscribe(result => this.list = result)
  }

}
