import { Pipe, PipeTransform } from '@angular/core';

const SUBJECTS:ReadonlyArray<string> = ['HTML', 'CSS', 'JS', 'Angular']

@Pipe({
  name: 'subjects',
  standalone: true
})
export class SubjectsPipe implements PipeTransform {

  transform(value: boolean[]): any {
    return SUBJECTS.filter((_, index) => value[index])
  }

}
