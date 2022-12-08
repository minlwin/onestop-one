import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-output-child',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './output-child.component.html',
  styles: [
  ]
})
export class OutputChildComponent {

  @Output() emitter = new EventEmitter<string>

}
