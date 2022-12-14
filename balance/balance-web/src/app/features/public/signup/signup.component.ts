import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  templateUrl: './signup.component.html',
  styles: [
  ]
})
export class SignupComponent {

  form:FormGroup

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      name: ['', Validators.required],
      phone: ['', Validators.required],
      email: ['', Validators.required]
    })
  }

  signUp() {
    if(this.form.valid) {

    }
  }

  isInvalid(name:string) {
    let control = this.form.get(name)

    if(control?.dirty && control.touched && !control.valid) {
      return true
    }
    return false
  }

}
