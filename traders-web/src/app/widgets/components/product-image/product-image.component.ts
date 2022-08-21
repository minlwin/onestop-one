import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-product-image',
  templateUrl: './product-image.component.html',
  styles: [
  ]
})
export class ProductImageComponent implements OnInit {

  @Input()
  image:string | undefined | null

  @Input()
  dummy:boolean = false

  @Input()
  height?:number = 240

  constructor() { }

  ngOnInit(): void {
  }

}
