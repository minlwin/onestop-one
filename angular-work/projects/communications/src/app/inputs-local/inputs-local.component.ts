import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InputLocalChildComponent } from './input-local-child/input-local-child.component';

@Component({
  standalone: true,
  imports: [CommonModule, InputLocalChildComponent],
  templateUrl: './inputs-local.component.html',
})
export class InputsLocalComponent {

}
