import { SecurityContext } from './../../../services/security/sercurity-context';
import { ConversationService } from 'src/app/services/api/conversation.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Component } from '@angular/core';
import { LeftSideBar } from '../../member-layout';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  templateUrl: './message-list.component.html',
  styles: [
  ]
})
export class MessageListComponent extends LeftSideBar {

  form:FormGroup
  list:any[] = []
  type?:string

  constructor(
    builder:FormBuilder,
    route:ActivatedRoute,
    context:SecurityContext,
    private router:Router,
    private service:ConversationService) {
    super()
    this.form = builder.group({
      sender: '',
      owner: '',
      keyword: '',
      from: ''
    })

    route.params.subscribe(param => {

      this.type = param['type']

      if(this.type == 'in') {
        this.form.removeControl('sender')
        this.form.patchValue({'owner' : context.security?.id})
      }

      if(this.type == 'out') {
        this.form.removeControl('owner')
        this.form.patchValue({'sender' : context.security?.id})
      }

      this.search()
    })
  }

  search() {
    this.service.search(this.form.value).subscribe(result => {
      this.list = result
    })
  }

  showDetails(product:number, sender:number) {
    this.router.navigate(['/member', 'message', 'details'],
      {queryParams: {
        product: product,
        sender: sender
      }})
  }

}
