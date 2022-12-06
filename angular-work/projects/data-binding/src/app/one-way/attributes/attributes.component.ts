import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  imports: [CommonModule],
  templateUrl: './attributes.component.html',
  styles: [
  ]
})
export class AttributesComponent {

  min = 0
  max = 500
  value = 100
}
