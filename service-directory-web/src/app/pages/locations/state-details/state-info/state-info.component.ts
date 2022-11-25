import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-state-info',
  templateUrl: './state-info.component.html',
  styles: [
  ]
})
export class StateInfoComponent {

  @Input()
  data:any
}
