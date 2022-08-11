import { LocationService } from './../../../../services/api/location.service';
import { EditProfileState } from './../../edit-profile.state';
import { FormGroup } from '@angular/forms';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-shipping-edit-form',
  templateUrl: './shipping-edit-form.component.html',
  styles: [
  ]
})
export class ShippingEditFormComponent implements OnInit {

  @Input()
  form!:FormGroup

  @Input()
  index!:number

  @Output()
  deleteEvent = new EventEmitter

  divisions:any[] = []
  townships:any[] = []

  constructor(private state:EditProfileState, private locations:LocationService) { }

  ngOnInit(): void {
    this.locations.findDivisions().subscribe(data => {
      this.divisions = data
    })
  }

  get formStyleClass() {
    return this.index < this.state.address.controls.length - 1 ? 'pb-2 border-bottom border-1' : '';
  }

  removeItem() {
    this.deleteEvent.emit(this.index)
  }

  changeDivision(select:string) {
    this.townships = []
    this.form.patchValue({township: ''})

    if(select != "") {
      this.locations.findTownships(select).subscribe(result => this.townships = result)
    }
  }
}
