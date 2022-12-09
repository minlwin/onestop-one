import { Component, Input } from '@angular/core';
import { MessageHolder } from '../../services/message.holder';

@Component({
  selector: 'page-parent',
  templateUrl: './parent.component.html',
})
export class ParentComponent {

  @Input()
  title?:string

  constructor(public messageHolder:MessageHolder) {}
}
