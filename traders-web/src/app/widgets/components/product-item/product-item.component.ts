import { ImgTwoComponent } from './img-two/img-two.component';
import { ImgThreeComponent } from './img-three/img-three.component';
import { ImgFourComponent } from './img-four/img-four.component';
import { ImgComponent } from './img-component';
import { ImageHostDirective } from './image-host.directive';
import { Component, Input, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { ImgOneComponent } from './img-one/img-one.component';

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styles: [
  ]
})
export class ProductItemComponent implements OnInit {

  @Input()
  item!:any

  @ViewChild(ImageHostDirective, {static: true})
  imgHost!:ImageHostDirective

  constructor() { }

  ngOnInit(): void {
    let viewRef = this.imgHost.containerRef
    viewRef.clear()
    let imageComponent = viewRef.createComponent(this.getImageComponent(this.item.images.length))
    imageComponent.instance.images = this.item.images
  }

  private getImageComponent(size:number) {
    switch(size) {
    case 0:
    case 1:
      return ImgOneComponent
    case 2:
      return ImgTwoComponent
    case 3:
      return ImgThreeComponent
    default:
      return ImgFourComponent
    }
  }

}
