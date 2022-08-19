import { ProductSerivce } from './../../../services/api/product.service';
import { ProductEditState } from './product-edit.state';
import { Component, OnInit } from '@angular/core';

@Component({
  templateUrl: './product-edit.component.html',
  providers: [
    ProductEditState
  ]
})
export class ProductEditComponent implements OnInit {

  constructor(public state:ProductEditState, private service:ProductSerivce) { }

  ngOnInit(): void {
  }

  get showPhotoBtn() {
    return this.state.form.get('id')?.value > 0
  }

  get showAddFeaturesBtn() {
    return (!this.state.readonly) && this.state.view == 'features'
  }

  save() {
    if(this.state.form.valid) {
      this.service.save(this.state.formValue)
        .subscribe(result => this.state.init(result))
    }
  }
}
