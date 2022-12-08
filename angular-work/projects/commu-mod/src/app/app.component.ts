import { Component, ViewChild } from '@angular/core';
import { Subject } from 'rxjs';
import { ChildInputComponent } from './child-input/child-input.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: []
})
export class AppComponent {

  list:string[] = []

  subject = new Subject<string>

  @ViewChild(ChildInputComponent)
  childInput?:ChildInputComponent

  constructor() {
    this.subject.subscribe(value => this.list.push(value))
  }
}
