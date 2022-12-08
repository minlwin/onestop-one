import { Component, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputViewChildComponent } from './input-view-child/input-view-child.component';

@Component({
  standalone: true,
  imports: [CommonModule, InputViewChildComponent],
  templateUrl: './inputs-view.component.html',
  styles: [
  ]
})
export class InputsViewComponent {

  @ViewChild(InputViewChildComponent)
  child?:InputViewChildComponent

  addData(str:string) {
    if(str) {
      this.child?.list.push(str)
    }
  }
}
