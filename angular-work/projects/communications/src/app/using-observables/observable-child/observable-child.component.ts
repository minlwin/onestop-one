import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-observable-child',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './observable-child.component.html',
  styles: [
  ]
})
export class ObservableChildComponent {

  subject = new Subject<string>

  @Input()
  set listener(subscriber:FunctionStringCallback) {
    this.subject.subscribe(subscriber)
  }

  add(value:string) {
    if(value) {
      this.subject.next(value)
    }
  }
}
