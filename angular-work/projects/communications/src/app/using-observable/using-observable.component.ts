import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ObservableChildComponent } from './observable-child/observable-child.component';
import { Subject } from 'rxjs';

@Component({
  standalone: true,
  imports: [CommonModule, ObservableChildComponent],
  templateUrl: './using-observable.component.html',
  styles: [
  ]
})
export class UsingObservableComponent {

  list:string[] = []

  subject = new Subject<string>

  constructor() {
    this.subject.subscribe(data => this.list.push(data))
  }
}
