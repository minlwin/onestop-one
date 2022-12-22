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
  calResult:number[] = []

  constructor(builder:FormBuilder, private service:BalanceService) {
    this.form = builder.group({
      from: '',
      to: ''
    })

    this.search()
  }

  search() {
    this.service.search(this.form.value)
      .subscribe(result => {
        this.list = handleApiResult(result)
        this.calResult= []

        let total = 0
        for (const item of this.list) {
          total = item.category.type == 'Credit' ? total + item.totalAmount : total - item.totalAmount
          this.calResult.push(total)
        }
      })
  }
}
