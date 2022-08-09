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

  divisions:any[] = []

  constructor(private state:EditProfileState, private locations:LocationService) {
    state.view = 'ship'
  }

  ngOnInit(): void {
    this.locations.findDivisions().subscribe(data => this.divisions = data)
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
