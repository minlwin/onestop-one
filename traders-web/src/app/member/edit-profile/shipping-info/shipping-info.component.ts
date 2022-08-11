import { FormArray, FormGroup } from '@angular/forms';
import { EditProfileState } from './../edit-profile.state';
import { Component, OnInit } from '@angular/core';
import { LocationService } from 'src/app/services/api/location.service';

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

}
