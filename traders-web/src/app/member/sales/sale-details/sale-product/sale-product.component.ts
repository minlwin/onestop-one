import { Component } from '@angular/core';
import { SaleState } from '../sale-state';

@Component({
  selector: 'app-sale-product',
  templateUrl: './sale-product.component.html',
  styles: [
  ]
})
export class SaleProductComponent  {

  get product() {
    return this.state.product
  }

  constructor(private state:SaleState) { }

  get image() {
    return this.product?.photos[0] as string || ''
  }

  get info() {
    let {... info} = this.product?.features || {}
    return Object.entries(info)
  }
}
