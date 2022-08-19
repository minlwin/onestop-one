import { ProductEditState } from './../product-edit.state';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product-features',
  templateUrl: './product-features.component.html',
  styles: [
  ]
})
export class ProductFeaturesComponent implements OnInit {

  constructor(public state:ProductEditState) {
    this.state.view = "features"
  }

  ngOnInit(): void {
  }

}
