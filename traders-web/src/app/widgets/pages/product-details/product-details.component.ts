import { ProductSerivce } from './../../../services/api/product.service';
import { map, mergeMap } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { SecurityContext } from '../../../services/security/sercurity-context';
import { LeftSideBar } from 'src/app/member/member-layout';
import { ConversationService } from 'src/app/services/api/conversation.service';

@Component({
  templateUrl: './product-details.component.html',
  styles: [
  ]
})
export class ProductDetailsComponent extends LeftSideBar{

  data?:any
  coverImage:string | undefined | null
  messages:any[] = []

  constructor(
      route:ActivatedRoute,
      service:ProductSerivce,
      messageService:ConversationService,
      private securityContext:SecurityContext,
      private router:Router) {
    super()
    // Loading Data
    route.params.pipe(
      map(param => param['id']),
      mergeMap(id => service.findById(id))
    ).subscribe(result => {
      this.data = result
      if(this.data.photos.length > 0) {
        this.coverImage = this.data.photos[0]
      }

      if(this.ownProduct) {
        messageService.search({product: this.data.id}).subscribe(list => {
          this.messages = list
        })
      } else {
        this.messages = []
      }
    })
  }

  get ownProduct() {
    return this.securityContext.security?.id == this.data?.sellerId
  }

  purchase(id:number) {

    let routeInfo = ['/member', 'purchase', id]

    if(this.securityContext.security) {
      this.router.navigate(routeInfo)
    } else {
      this.router.navigate(['/public', 'signin'], {queryParams: {target: routeInfo}})
    }

  }

  askQuestion(id:number) {
    let routeInfo = ['/member', 'message', 'details']
    let queryparam = {product: id}


    if(this.securityContext.security) {
      this.router.navigate(routeInfo, {queryParams: queryparam})
    } else {
      this.router.navigate(['/public', 'signin'],
        {queryParams: {route: JSON.stringify(routeInfo), query : JSON.stringify(queryparam)}})
    }

  }

  showDetails(product:number, sender:number) {
    this.router.navigate(['/member', 'message', 'details'],
      {queryParams: {
        product: product,
        sender: sender
      }})
  }

  get features() {
    return Object.keys(this.data?.features || {})
  }
}
