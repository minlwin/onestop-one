import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'amountOf'
})
export class AmountByTypePipe implements PipeTransform {

  transform(value: any, type:string, position:string) {
    return type == position ? value : 0
  }


}
