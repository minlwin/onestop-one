import { LocationService } from './../../../../services/api/location.service';
import { EditProfileState } from './../../edit-profile.state';
import { FormGroup } from '@angular/forms';
import { Component, EventEmitter, Input, OnInit, Output, ElementRef, ViewChild } from '@angular/core';

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

  @Input()
  readonly:boolean = true

  @Output()
  deleteEvent = new EventEmitter

  @ViewChild('divisionSelect')
  divisionSelect?:ElementRef

  divisions:any[] = []
  townships:any[] = []

  selectedTownship:any = {}

  constructor(
    private state:EditProfileState,
    private locations:LocationService) { }

  ngOnInit(): void {
    this.locations.findDivisions().subscribe(data => {
      this.divisions = data

      const townshipId = this.form.get('township')?.value

      if(townshipId) {
        this.locations.findTownshipById(townshipId).subscribe(result => {
          this.selectedTownship = result
          if(this.divisionSelect) {
            this.divisionSelect.nativeElement.value = result.divisionId
            this.locations.findTownships(result.divisionId).subscribe(result => {
              this.townships = result
              this.form.patchValue({township: this.selectedTownship.id})
            })
          }
        })
      }
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
    this.selectedTownship = undefined
    this.form.patchValue({township: ''})

    if(select != "") {
      this.locations.findTownships(select).subscribe(result => this.townships = result)
    }
  }
}
