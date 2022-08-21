import { ProductSerivce } from './../../../services/api/product.service';
import { map, tap, mergeMap } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { SecurityContext } from '../../../services/security/sercurity-context';

@Component({
  templateUrl: './product-details.component.html',
  styles: [
  ]
})
export class ProductDetailsComponent implements OnInit {

  data?:any
  coverImage:string | undefined | null

  constructor(
    route:ActivatedRoute,
    service:ProductSerivce,
    private securityContext:SecurityContext,
    private router:Router) {
    route.params.pipe(
      map(param => param['id']),
      mergeMap(id => service.findById(id))
    ).subscribe(result => {
      this.data = result
      this.coverImage = this.data.photos[0]
    })
  }

  ngOnInit(): void {
  }

  get ownProduct() {
    return this.securityContext.security?.id == this.data?.sellerId
  }

  purchase(id:number) {

    let routeInfo = ['/member', 'purchase', id]

    if(this.securityContext.security) {
      this.router.navigate(routeInfo)
    }

    this.router.navigate(['/public', 'signin'], {queryParams: {target: routeInfo}})
  }

  askQuestion() {
    if(this.securityContext.security) {

    }

    this.router.navigate(['/public', 'signin'])
  }

  conversations() {

  }

  editProduct() {

  }

  get features() {
    return Object.keys(this.data?.features || {})
  }
}
