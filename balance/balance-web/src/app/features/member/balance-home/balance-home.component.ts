import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { handleApiResult } from 'src/app/shared/services';
import { BalanceService } from 'src/app/shared/services/balance.service';

@Component({
  templateUrl: './balance-home.component.html',
  styles: [
  ]
})
export class BalanceHomeComponent {

  form:FormGroup
  list:any[] = []

  constructor(builder:FormBuilder, private service:BalanceService) {
    this.form = builder.group({
      from: '',
      to: ''
    })
  }

  search() {
    this.service.search(this.form.value)
      .subscribe(result => this.list = handleApiResult(result))
  }
}
