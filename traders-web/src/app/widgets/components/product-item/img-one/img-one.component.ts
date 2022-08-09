import { Component, Input, OnInit } from '@angular/core';
import { ImgComponent } from '../img-component';

@Component({
  selector: 'app-img-one',
  templateUrl: './img-one.component.html',
})
export class ImgOneComponent implements ImgComponent, OnInit {

  constructor() { }

  @Input()
  images: string[] = []

  ngOnInit(): void {
  }

  get image() {
    return this.images.length > 0 ? this.images[0] : 'default-image.png'
  }

}
