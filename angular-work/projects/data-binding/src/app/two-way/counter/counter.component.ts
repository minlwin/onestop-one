import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-counter',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './counter.component.html',
  styles: [
  ]
})
export class CounterComponent {

  @Input()
  count = 0

  @Output()
  countChange = new EventEmitter
}
