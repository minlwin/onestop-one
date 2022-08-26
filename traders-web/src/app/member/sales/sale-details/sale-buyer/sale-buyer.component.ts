import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { LocationService } from 'src/app/services/api/location.service';
import { SaleState } from '../sale-state';
import { ProfileService } from 'src/app/services/api/profile.service';

declare const bootstrap:any

@Component({
  selector: 'app-sale-buyer',
  templateUrl: './sale-buyer.component.html',
  styles: [
  ]
})
export class SaleBuyerComponent implements OnInit{

  divisions:any[] = []
  townships:any[] = []
  form:FormGroup

  modalDialog:any

  constructor(
    builder:FormBuilder,
    private state:SaleState,
    private profService:ProfileService,
    private locationService:LocationService) {

    locationService.findDivisions().subscribe(result => this.divisions = result)

    this.form = builder.group({
      id: 0,
      name: ['', Validators.required],
      address: ['', Validators.required],
      township: ['', Validators.required],
      deleted: false,
      division: ''
    })
  }

  ngOnInit(): void {
    this.modalDialog = new bootstrap.Modal('#shippingModal', {backdrop : false})
  }

  get profile() {
    return this.state.buyer
  }

  get selectedAddress() {
    return this.state.selectedShippingAddress
  }

  get addressList() {
    const addresses = this.profile?.address as any [] || []
    return addresses.filter(a => !a.deleted)
  }

  get hideSelectAddress() {
    return this.addressList.length <= 1
  }

  get buyerView() {
    return this.state.buyerView && this.state.preorderState
  }

  addNewAddressOpen() {

    this.state.orderForm.patchValue({shipping: ''})

    this.form.patchValue({
      id: 0,
      name: '',
      address: '',
      township: '',
      deleted: false,
      division: ''
    })

    // Show Modal Dialog
    this.modalDialog.show()
  }

  changeDivision(id:string) {
    this.townships = []
    if(id) {
      this.locationService.findTownships(id).subscribe(result => {
        this.townships = result
      })
    }
  }

  changeAddress(address:any) {
    this.profService.findAddressById(address).subscribe(result => {
      this.state.setShippingAddress(result)
    })
  }

  saveAddress() {
    if(this.form?.valid) {

      const {division, ... addressData} = this.form.value

      this.profService.addAddress(this.state.buyer.id, addressData)
        .subscribe(result => {
          this.state.setShippingAddress(result)
        })
    }
  }
}
