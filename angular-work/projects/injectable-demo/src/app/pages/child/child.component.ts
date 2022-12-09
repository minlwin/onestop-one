import { Component } from '@angular/core';
import { MessageHolder } from '../../services/message.holder';

@Component({
  selector: 'message-edit',
  templateUrl: './child.component.html',
})
export class ChildComponent {

  constructor(public service:MessageHolder) {}
}
