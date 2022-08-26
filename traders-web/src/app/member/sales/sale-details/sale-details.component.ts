import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FullScreen } from '../../member-layout';
import { SaleState } from './sale-state';

@Component({
  templateUrl: './sale-details.component.html',
  providers: [
    SaleState
  ]
})
export class SaleDetailsComponent extends FullScreen  {


  constructor(
    route:ActivatedRoute,
    public state:SaleState
    ) {
    super()

    route.queryParams.subscribe(params => {

      if(params['id']) {
        state.initViewBySaleId(params['id'])
      }

      if(params['product']) {
        state.initViewByProductId(params['product'])
       }
    })
  }

}
