import { AfterViewInit, Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

declare const bootstrap:any

@Component({
  selector: 'app-change-pass',
  templateUrl: './change-pass.component.html',
  styles: [
  ]
})
export class ChangePassComponent implements AfterViewInit {

  private dialog:any
  form:FormGroup
  @Output() onSave = new EventEmitter

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      oldPass: ['', Validators.required],
      newPass: ['', Validators.required],
    })
  }

  ngAfterViewInit(): void {
    this.dialog = new bootstrap.Modal('#changePassword', {backdrop: false})
  }

  save() {
    if(this.form.valid) {
      this.onSave.emit(this.form.value)
    }
  }

  show() {
    this.form.patchValue({
      oldPass: '',
      newPass: ''
    })
    this.dialog.show()
  }

  hide() {
    this.dialog.hide()
  }

  hasError(name:string) {
    return (this.form.get(name)?.dirty || this.form.get('name')?.touched) && !this.form.get(name)?.valid
  }

}
