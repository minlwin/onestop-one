import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OutputChildComponent } from './output-child/output-child.component';

@Component({
  standalone: true,
  imports: [CommonModule, OutputChildComponent],
  templateUrl: './using-outputs.component.html',
  styles: [
  ]
})
export class UsingOutputsComponent {

  list:string[] = []


}
