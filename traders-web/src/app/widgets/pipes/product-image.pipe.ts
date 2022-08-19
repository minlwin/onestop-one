import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'productImage'
})
export class ProductImagePipe implements PipeTransform {

  transform(value: string, dummy: boolean = false): unknown {

    if(value && dummy) {
      return `/assets/images/${value}`
    }

      return `/api/photo/${value}`
  }

}
