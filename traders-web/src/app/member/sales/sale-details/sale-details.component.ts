import { ProductSerivce } from './../../../services/api/product.service';
import { SecurityContext } from 'src/app/services/security/sercurity-context';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LeftSideBar } from '../../member-layout';
import { ProfileService } from 'src/app/services/api/profile.service';
import { SaleService } from 'src/app/services/api/sale.service';

@Component({
  templateUrl: './sale-details.component.html',
  styles: [
  ]
})
export class SaleDetailsComponent extends LeftSideBar  {

  product:any = {}
  seller:any = {}
  buyer:any = {}

  orderForm:FormGroup
  shippingForm?:FormGroup

  constructor(
    route:ActivatedRoute,
    context:SecurityContext,
    private builder:FormBuilder,
    private saleService:SaleService,
    private prodService:ProductSerivce,
    private profService:ProfileService
    ) {
    super()

    this.orderForm = builder.group({
      product: ['', Validators.required],
      buyer: ['', Validators.required],
      shipping: ['', Validators.required]
    })

    route.queryParams.subscribe(params => {

      if(params['id']) {
        this.initViewBySaleId(params['id'])
      }

      if(params['product']) {
        this.initViewByProductId(params['product'])

        profService.findById(context.security?.id!).subscribe(result => {
          this.initBuyer(result)
        })
       }
    })
  }

  initViewBySaleId(id:number) {
    this.saleService.findById(id).subscribe(result => {
      this.initProduct(result.product)

      this.profService.findById(result.buyerId).subscribe(result => {
        this.initBuyer(result)
      })

    })
  }

  initViewByProductId(id:number) {
    this.prodService.findById(id).subscribe(result => {
      this.initProduct(result)
    })
  }

  initProduct(result:any) {
    this.product = result
    this.orderForm.patchValue({product: result.id})

    this.profService.findById(this.product.sellerId).subscribe(profile => {
      this.seller = profile
      this.orderForm.patchValue({seller: profile.id})
    })
  }

  initBuyer(result:any) {
    this.buyer = result
    this.orderForm.patchValue({buyer: result.id})

    if(!result.address || result.addres.length == 0) {
      this.shippingForm = this.builder.group({

      })
    }
  }
}
