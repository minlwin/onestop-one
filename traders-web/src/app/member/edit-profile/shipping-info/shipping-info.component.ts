import { FormArray, FormGroup } from '@angular/forms';
import { EditProfileState } from './../edit-profile.state';
import { Component, OnInit } from '@angular/core';

@Component({
  templateUrl: './shipping-info.component.html',
  styles: [
  ]
})
export class ShippingInfoComponent implements OnInit {

  constructor(private state:EditProfileState) {
    state.view = 'ship'
  }

  ngOnInit(): void {
  }

  get addresses() {
    return this.state.address
  }

  getFormStyleClass(i:number) {
    return i < this.addresses.controls.length - 1 ? 'pb-2 border-bottom border-1' : '';
  }

  removeItem(index:number) {
    this.addresses.removeAt(index)
  }

}
