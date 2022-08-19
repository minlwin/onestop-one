import { ProductEditState } from './../product-edit.state';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product-photos',
  templateUrl: './product-photos.component.html',
  styles: [
  ]
})
export class ProductPhotosComponent implements OnInit {

  constructor(public state:ProductEditState) {
    state.view = "photos"
  }

  ngOnInit(): void {
  }

}
