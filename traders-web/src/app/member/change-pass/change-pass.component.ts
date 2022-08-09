import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  templateUrl: './change-pass.component.html',
  styles: [
  ]
})
export class ChangePassComponent implements OnInit {

  form:FormGroup

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      oldPass: ['', [Validators.required, Validators.minLength(8)]],
      newPass: ['', [Validators.required, Validators.minLength(8)]],
    })
  }

  ngOnInit(): void {
  }

  changePass() {

  }

}
