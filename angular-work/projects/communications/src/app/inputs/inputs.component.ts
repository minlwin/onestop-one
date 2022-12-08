import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputChildComponent } from './input-child/input-child.component';

@Component({
  standalone: true,
  imports: [CommonModule, InputChildComponent],
  templateUrl: './inputs.component.html'
})
export class InputsComponent {

  private _list:any[] = [
    {id: 1, name: 'Java Basic', fees: 300000, duration: 4},
    {id: 2, name: 'Spring Framework', fees: 500000, duration: 6},
    {id: 3, name: 'One Stop', fees: 750000, duration: 6},
    {id: 4, name: 'Flutter', fees: 300000, duration: 4}
  ]

  targetIndex = 0

  get childData() {
    return this._list[this.targetIndex]
  }

  get list() {
    return this._list
  }
}
