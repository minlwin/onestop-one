import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-district-info',
  templateUrl: './district-info.component.html',
  styles: [
  ]
})
export class DistrictInfoComponent {

  @Input()
  data:any

  @Output()
  onEdit = new EventEmitter<any>
}
