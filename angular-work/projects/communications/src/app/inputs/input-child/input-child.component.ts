import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-input-child',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './input-child.component.html',
})
export class InputChildComponent implements OnChanges{

  @Input()
  set data(value:any) {
    console.log("Set Value")
    this.dto = value
  }

  dto:any

  ngOnChanges(changes: SimpleChanges): void {
    console.log("On Change")
    console.log(changes)
  }
}
