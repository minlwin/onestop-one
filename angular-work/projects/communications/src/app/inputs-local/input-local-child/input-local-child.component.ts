import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-input-local-child',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './input-local-child.component.html',
  styles: [
  ]
})
export class InputLocalChildComponent {

  list:string[] = []
}
