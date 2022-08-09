import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'backgroundImage'
})
export class BackgroundImagePipe implements PipeTransform {

  transform(value?: string): string {
    let url = value ? value : 'default.png'
    return `url(/assets/images/${url})`
  }

}
