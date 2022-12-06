import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CounterComponent } from './counter/counter.component';

@Component({
  standalone: true,
  imports: [CommonModule, FormsModule, CounterComponent],
  templateUrl: './two-way.component.html',
  styles: [
  ]
})
export class TwoWayComponent {

  value = ''

  formData = ''

  counterValue:any = 0
}
