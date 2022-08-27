import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component } from '@angular/core';
import { ProfileService } from 'src/app/services/api/profile.service';
import { SaleState } from '../sale-state';

@Component({
  selector: 'app-sale-seller',
  templateUrl: './sale-seller.component.html',
  styles: [
  ]
})
export class SaleSellerComponent {

  form:FormGroup

  constructor(
    builder:FormBuilder,
    private state:SaleState,
    private profService:ProfileService) {
    this.form = builder.group({
      id: 0,
      type: ['', Validators.required],
      accountName: ['', Validators.required],
      accountNumber: ['', Validators.required],
      deleted: false
    })
  }

  get profile() {
    return this.state.seller
  }

  get sellerView() {
    return this.state.sellerView
  }

  get bankings():any[] {
    const list:any[] = this.profile?.bankingInfo || []
    return list.filter(a => !a.deleted) || []
  }

  saveBankingInfo() {
    if(this.form?.valid) {
      this.profService.addBanking(this.profile.id, this.form?.value)
        .subscribe(result => {
          this.state.initSeller(result)
          this.clearForm()
        })
    }
  }

  clearForm() {
    this.form.patchValue({
      id: 0,
      type: '',
      accountName: '',
      accountNumber: '',
      deleted : false
    })
  }

}
