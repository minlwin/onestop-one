import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListenerChildComponent } from './listener-child/listener-child.component';

export interface StringValueListener {
  take(value:string):void
}

@Component({
  standalone: true,
  imports: [CommonModule, ListenerChildComponent],
  templateUrl: './using-listener.component.html',
  styles: [
  ]
})
export class UsingListenerComponent implements OnInit{

  list:string[] = []

  valueListener?:StringValueListener

  ngOnInit(): void {
    this.valueListener = this
  }

  take(value:string) {
    this.list.push(value)
  }
}
