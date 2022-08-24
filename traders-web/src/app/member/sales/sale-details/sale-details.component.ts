import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LeftSideBar } from '../../member-layout';

@Component({
  templateUrl: './sale-details.component.html',
  styles: [
  ]
})
export class SaleDetailsComponent extends LeftSideBar  {

  constructor(route:ActivatedRoute) {
    super()
  }

}
