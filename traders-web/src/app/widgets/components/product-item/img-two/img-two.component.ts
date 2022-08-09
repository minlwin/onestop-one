import { ImgComponent } from './../img-component';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-img-two',
  templateUrl: './img-two.component.html',
  styles: [
  ]
})
export class ImgTwoComponent implements ImgComponent, OnInit {

  @Input()
  images: string[] = []

  constructor() { }

  ngOnInit(): void {
  }

}
