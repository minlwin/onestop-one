import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-township-edit',
  templateUrl: './township-edit.component.html',
  styles: [
  ]
})
export class TownshipEditComponent {

  form:FormGroup

  @Output()
  onSave = new EventEmitter<any>

  @Input()
  districtName = ''

  @Input()
  set data(data:any) {
    this.initForm()
    this.form.patchValue(data)
  }

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      id: 0,
      districtId: [0, Validators.min(1)],
      name: ['', Validators.required],
      burmeseName: ['', Validators.required],
      deleted: false
    })
  }

  save() {
    if(this.form.valid) {
      this.onSave.emit(this.form.value)
    }
  }

  private initForm() {
    this.form.patchValue({
      id: 0,
      districtId: 0,
      name: '',
      burmeseName: '',
      delete: false
    })
  }
}
