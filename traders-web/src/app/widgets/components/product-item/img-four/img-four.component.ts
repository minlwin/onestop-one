import { ImgComponent } from './../img-component';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-img-four',
  templateUrl: './img-four.component.html',
  styles: [
  ]
})
export class ImgFourComponent implements ImgComponent, OnInit {

  @Input()
  images: string[] = []

  constructor() { }

  ngOnInit(): void {
  }

}
