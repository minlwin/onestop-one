import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'compactNumber'
})
export class CompactNumberPipe implements PipeTransform {

  transform(value: number): string {
    let formatter = Intl.NumberFormat('en', {notation: 'compact'})
    return formatter.format(value)
  }

}
