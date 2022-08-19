import { ProductEditState } from './../product-edit.state';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product-info',
  templateUrl: './product-info.component.html',
  styles: [
  ]
})
export class ProductInfoComponent implements OnInit {

  constructor(public state:ProductEditState) {
    this.state.view = "info"
  }

  ngOnInit(): void {
  }

}
