import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'apiDates'
})
export class ApiDatesPipe implements PipeTransform {

  transform(value: any): unknown {
    if(value) {
      const array = value as number[]
      return array.join("-");
    }
    return ''
  }

}
