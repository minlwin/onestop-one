import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-child-input',
  templateUrl: './child-input.component.html',
  styles: [
  ]
})
export class ChildInputComponent {

  @Input()
  subject = new Subject<string>

  list:string[] = []

}
