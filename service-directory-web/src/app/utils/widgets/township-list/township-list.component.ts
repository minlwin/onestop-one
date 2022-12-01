import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-township-list',
  templateUrl: './township-list.component.html',
  styles: [
  ]
})
export class TownshipListComponent {

  @Input()
  list:any[] = []

  @Output()
  onEdit = new EventEmitter<any>

  edit(data:any) {
    this.onEdit.emit(data)
  }
}
