import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChildComponent } from '../child/child.component';

@Component({
  standalone: true,
  imports: [CommonModule, ChildComponent],
  templateUrl: './parent.component.html',
})
export class ParentComponent {

  list:string[] = []

  addData(data:string) {
    this.list.push(data)
  }
}
