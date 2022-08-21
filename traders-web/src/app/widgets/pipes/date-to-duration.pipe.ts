import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateToDuration'
})
export class DateToDurationPipe implements PipeTransform {

  transform(value: string): any {
    if(value) {
      const seconds = Math.floor((+new Date() - +new Date(value)) / 1000)
      return intervals.map(interval => {
          let reminder = Math.floor(seconds / interval[1])
          return {reminder: reminder, interval: interval[0]}
        })
        .filter(result => result.reminder > 0)
        .map(result => `${result.reminder} ${result.interval}${result.reminder == 0 ? '' : 's'}`)
        .shift()
    }
    return value;
  }
}

const intervals: [string, number][] = [
  ['year', 31536000],
  ['month', 2592000],
  ['week', 604800],
  ['day', 86400],
  ['hour', 3600],
  ['minute', 60],
  ['second', 1]
]


