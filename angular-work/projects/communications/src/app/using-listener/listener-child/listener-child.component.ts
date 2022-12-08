import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StringValueListener } from '../using-listener.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-listener-child',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './listener-child.component.html',
  styles: [
  ]
})
export class ListenerChildComponent {

  value:string = ''

  @Input()
  listener:StringValueListener | null | undefined

  add() {
    if(this.value && this.listener) {
      this.listener.take(this.value)
      this.value = ""
    }
  }
}
