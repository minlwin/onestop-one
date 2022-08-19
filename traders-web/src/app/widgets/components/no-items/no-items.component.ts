import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-no-items',
  templateUrl: './no-items.component.html',
  styles: [
  ]
})
export class NoItemsComponent implements OnInit {

  @Input()
  message?:string

  constructor() { }

  ngOnInit(): void {
  }

}
