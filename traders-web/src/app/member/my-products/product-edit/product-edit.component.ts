import { ProductEditState } from './product-edit.state';
import { Component, OnInit } from '@angular/core';

@Component({
  templateUrl: './product-edit.component.html',
  providers: [
    ProductEditState
  ]
})
export class ProductEditComponent implements OnInit {

  constructor(public state:ProductEditState) { }

  ngOnInit(): void {
  }

  get showPhotoBtn() {
    return (!this.state.readonly) && this.state.view == 'photos'
  }

  get showAddFeaturesBtn() {
    return (!this.state.readonly) && this.state.view == 'features'
  }
}
