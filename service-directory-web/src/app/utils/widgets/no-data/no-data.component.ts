import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-no-data',
  templateUrl: './no-data.component.html',
  styles: [
  ]
})
export class NoDataComponent {

  @Input()
  dataName?:string
}
