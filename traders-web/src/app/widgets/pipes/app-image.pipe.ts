import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'appImage'
})
export class AppImagePipe implements PipeTransform {

  transform(value: string | undefined | null, type: 'product' | 'profile' = 'product'): string {
    if(value) {
      return `/api/photo/${value}`
    }
    return `/assets/images/default-${type}.png`;
  }

}
