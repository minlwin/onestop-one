import { Component } from '@angular/core';
import { SaleState } from '../sale-state';

@Component({
  selector: 'app-sale-seller',
  templateUrl: './sale-seller.component.html',
  styles: [
  ]
})
export class SaleSellerComponent {

  get profile() {
    return this.state.seller
  }

  constructor(private state:SaleState) { }

  get sellerView() {
    return this.state.sellerView
  }

  get bankings():any[] {
    const list:any[] = this.profile?.bankingInfo || []
    return list.filter(a => !a.deleted) || []
  }
}
