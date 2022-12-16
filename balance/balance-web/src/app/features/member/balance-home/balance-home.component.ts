import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  templateUrl: './balance-home.component.html',
  styles: [
  ]
})
export class BalanceHomeComponent {

  form:FormGroup
  list:any[] = []

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      from: '',
      to: ''
    })
  }
}
