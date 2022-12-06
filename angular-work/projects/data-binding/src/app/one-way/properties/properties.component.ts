import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  standalone: true,
  imports: [CommonModule],
  templateUrl: './properties.component.html',
  styles: [
  ]
})
export class PropertiesComponent {
  title = "Hello Property Binding"
  colorStyle = "color : red; font-size : 3rem;"
}
