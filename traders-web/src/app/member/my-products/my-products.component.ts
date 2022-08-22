import { Component, OnInit } from '@angular/core';
import { LeftSideBar } from '../member-layout';

@Component({
  templateUrl: './my-products.component.html',
  styles: [
  ]
})
export class MyProductsComponent extends LeftSideBar implements OnInit {

  constructor() {
    super()
  }

  ngOnInit(): void {
  }

}
