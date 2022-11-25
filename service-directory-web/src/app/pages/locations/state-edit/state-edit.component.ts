import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { regionsConstant } from 'src/app/services';

@Component({
  selector: 'app-state-edit',
  templateUrl: './state-edit.component.html',
  styles: [
  ]
})
export class StateEditComponent {

  form:FormGroup
  regions = regionsConstant

  @Output()
  onSave = new EventEmitter<any>

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      id: 0,
      name: ['', Validators.required],
      burmeseName: ['', Validators.required],
      region: ['', Validators.required],
      capital: ['', Validators.required],
      deleted: false
    })
  }

  save() {
    if(this.form.valid) {
      this.onSave.emit(this.form.value)
    }
  }
}
