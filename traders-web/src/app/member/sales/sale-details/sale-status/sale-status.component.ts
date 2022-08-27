import { Component, OnInit } from '@angular/core';
import { SaleState } from '../sale-state';

@Component({
  selector: 'app-sale-status',
  templateUrl: './sale-status.component.html',
  styles: [
  ]
})
export class SaleStatusComponent implements OnInit {

  constructor(public state:SaleState) { }

  ngOnInit(): void {
  }

}
