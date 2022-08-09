import { Directive, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[appImageHost]'
})
export class ImageHostDirective {

  constructor(public containerRef:ViewContainerRef) { }

}
