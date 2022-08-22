import { Component, OnInit } from '@angular/core';
import { LeftSideBar } from '../member-layout';

@Component({
  templateUrl: './my-sales.component.html',
  styles: [
  ]
})
export class MySalesComponent extends LeftSideBar implements OnInit {

  constructor() {
    super()
  }

  ngOnInit(): void {
  }

}
