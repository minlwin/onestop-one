import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-state-info',
  templateUrl: './state-info.component.html',
  styles: [
  ]
})
export class StateInfoComponent {

  @Input()
  data:any

  @Output()
  onEdit = new EventEmitter<boolean>

  edit() {
    this.onEdit.emit(true)
  }
}
