import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Component } from '@angular/core';
import { SecurityContext } from 'src/app/services/security/sercurity-context';
import { FullScreen } from '../../member-layout';
import { ConversationService } from 'src/app/services/api/conversation.service';

@Component({
  templateUrl: './message-details.component.html',
  styles: [
  ]
})
export class MessageDetailsComponent extends FullScreen {

  form:FormGroup
  dto:any = {}

  currentImage?:string

  constructor(
    route:ActivatedRoute,
    builder:FormBuilder,
    context:SecurityContext,
    private service:ConversationService
  ) {

    super()

    this.form = builder.group({
      productId: 0,
      senderId: context.security?.id,
      messageSenderId: context.security?.id,
      message: ['', Validators.required]
    })

    route.queryParams.subscribe(params => {
      if(params['product']) {
        this.form.patchValue({'productId': params['product']})
      }

      if(params['sender']) {
        this.form.patchValue({'senderId': params['sender']})
      }

      this.loadData()
    })
  }

  private loadData() {
    this.service.findById(this.form.get('productId')?.value, this.form.get('senderId')?.value)
      .subscribe(result => this.initState(result))
  }

  private initState(result:any) {
    this.dto = result
    this.form.patchValue({message : ''})
    this.currentImage = undefined

    if(result.photos.length > 0) {
      this.currentImage = result.photos[0]
    }
  }

  sendMessage() {
    this.service.sendMessage(this.form.value).subscribe(result => {
      this.form.patchValue({message: ''})
      this.dto = result
    })
  }

  setCurrentImage(image:string) {
    this.currentImage = image
  }

}
