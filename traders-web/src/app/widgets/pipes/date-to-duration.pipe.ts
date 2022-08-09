import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateToDuration'
})
export class DateToDurationPipe implements PipeTransform {

  transform(value: Date) {
    return '4 Days';
  }

}
