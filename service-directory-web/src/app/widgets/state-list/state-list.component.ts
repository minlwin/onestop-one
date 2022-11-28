import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-state-list',
  templateUrl: './state-list.component.html',
  styles: [
  ]
})
export class StateListComponent {

  @Input()
  list:any[] = []

}
