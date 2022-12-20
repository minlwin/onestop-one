import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BalanceAppContext } from '../../services/balance-app.context';

declare const bootstrap:any

@Component({
  selector: 'app-profile-edit',
  templateUrl: './profile-edit.component.html',
  styles: [
  ]
})
export class ProfileEditComponent {

  private dialog:any
  form:FormGroup
  @Output() onSave = new EventEmitter

  constructor(builder:FormBuilder, private context:BalanceAppContext) {
    this.form = builder.group({
      name: ['', Validators.required],
      phone: ['', Validators.required],
    })
  }

  ngAfterViewInit(): void {
    this.dialog = new bootstrap.Modal('#profileEdit', {backdrop: false})
  }

  save() {
    if(this.form.valid) {
      this.onSave.emit(this.form.value)
    }
  }

  hide() {
    this.dialog.hide()
  }

  show() {
    this.form.patchValue({
      name: this.context.loginUser.name,
      phone: this.context.loginUser.phone,
      email: this.context.loginUser.email
    })
    this.dialog.show()
  }

  hasError(name:string) {
    return (this.form.get(name)?.dirty || this.form.get('name')?.touched) && !this.form.get(name)?.valid
  }

}
