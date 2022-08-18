import { EditProfileState } from './../edit-profile.state';
import { Component, OnInit } from '@angular/core';

@Component({
  templateUrl: './shipping-info.component.html',
  styles: [
  ]
})
export class ShippingInfoComponent implements OnInit {

  townships:any[] = []

  constructor(private state:EditProfileState) {
    state.view = 'ship'
  }

  ngOnInit(): void {
  }

  get addresses() {
    return this.state.address
  }

  removeItem(index:number) {
    this.addresses.removeAt(index)
  }

  get readonly() {
    return !this.state.editable
  }

}
