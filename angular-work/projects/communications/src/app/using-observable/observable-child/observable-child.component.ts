import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-observable-child',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './observable-child.component.html',
})
export class ObservableChildComponent {

  @Input()
  emitter?:Subject<string>
}
