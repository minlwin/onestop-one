import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { handleApiResult } from 'src/app/shared/services';
import { BalanceService } from 'src/app/shared/services/balance.service';

@Component({
  templateUrl: './balance-details.component.html',
  styles: [
  ]
})
export class BalanceDetailsComponent {

  balance:any

  constructor(route:ActivatedRoute, service:BalanceService) {
    route.queryParamMap.subscribe(params => {
      if(params.get('id')) {
        service.findById(+params.get('id')!).subscribe(result => {
          this.balance = handleApiResult(result)
        })
      }
    })
  }

  get details() {
    return this.balance?.details as any[] || []
  }

  get total() {
    return this.details.length > 0 ? this.details.map(a => a.quentity * a.unitPrice).reduce((a, b) => a + b) : 0
  }
}
