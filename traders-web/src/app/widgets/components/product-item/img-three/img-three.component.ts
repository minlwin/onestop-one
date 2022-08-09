import { ImgComponent } from './../img-component';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-img-three',
  templateUrl: './img-three.component.html',
  styles: [
  ]
})
export class ImgThreeComponent implements ImgComponent, OnInit {

  @Input()
  images: string[] = []

  constructor() { }

  ngOnInit(): void {
  }

}
