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

  constructor(private service:ProductSerivce, private context:SecurityContext) { }

  ngOnInit(): void {
    this.service.search({seller: this.context.security?.id}).subscribe(result => this.list = result)
  }

}
