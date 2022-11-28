import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-township-list',
  templateUrl: './township-list.component.html',
  styles: [
  ]
})
export class TownshipListComponent {

  @Input()
  list:any[] = []
}
