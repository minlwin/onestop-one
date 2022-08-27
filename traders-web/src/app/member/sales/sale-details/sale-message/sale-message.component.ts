import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sale-message',
  templateUrl: './sale-message.component.html',
  styles: [
  ]
})
export class SaleMessageComponent implements OnInit {

  messages:any[] = []

  constructor() { }

  ngOnInit(): void {
  }

}
