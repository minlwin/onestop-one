import { Component, OnInit } from '@angular/core';
import { LeftSideBar } from '../member-layout';

@Component({
  templateUrl: './my-purchases.component.html',
  styles: [
  ]
})
export class MyPurchasesComponent extends LeftSideBar implements OnInit {

  constructor() {
    super()
  }

  ngOnInit(): void {
  }

}
