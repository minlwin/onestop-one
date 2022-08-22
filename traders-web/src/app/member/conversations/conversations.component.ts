import { Component, OnInit } from '@angular/core';
import { FullScreen } from '../member-layout';

@Component({
  templateUrl: './conversations.component.html',
  styles: [
  ]
})
export class ConversationsComponent extends FullScreen implements OnInit {

  constructor() {
    super()
  }

  ngOnInit(): void {
  }

}
