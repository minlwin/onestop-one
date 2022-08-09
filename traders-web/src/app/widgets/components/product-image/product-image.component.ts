import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-product-image',
  templateUrl: './product-image.component.html',
  styles: [
  ]
})
export class ProductImageComponent implements OnInit {

  @Input()
  image?:string

  @Input()
  height?:number

  @Input()
  width?:number

  constructor() { }

  ngOnInit(): void {
  }

}
