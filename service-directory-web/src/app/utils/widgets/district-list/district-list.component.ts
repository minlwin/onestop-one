import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-district-list',
  templateUrl: './district-list.component.html',
  styles: [
  ]
})
export class DistrictListComponent {

  @Input()
  list:any[] = []
}
