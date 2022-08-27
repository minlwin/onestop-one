import { SecurityContext } from 'src/app/services/security/sercurity-context';
import { ProfileService } from './../../../services/api/profile.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Injectable } from "@angular/core";
import { SaleService } from 'src/app/services/api/sale.service';
import { ProductSerivce } from 'src/app/services/api/product.service';

@Injectable()
export class SaleState {

  saleId:number = 0
  product:any
  seller:any
  buyer:any

  selectedShippingAddress:any

  orderForm:FormGroup
  status:any

  constructor(
    builder:FormBuilder,
    private context:SecurityContext,
    private prodService:ProductSerivce,
    private profService:ProfileService,
    private saleService:SaleService) {

      this.orderForm = builder.group({
        product: ['', [Validators.required, Validators.min(1)]],
        buyer: ['', [Validators.required, Validators.min(1)]],
        shipping: ['', [Validators.required, Validators.min(1)]]
      })

  }

  get sellerView() {
    return this.context.security?.id == this.seller?.id
  }

  get buyerView() {
    return this.context.security?.id == this.buyer?.id
  }

  get preorderState() {
    return this.saleId == 0
  }

  initViewBySaleId(id:number) {
    this.saleService.findById(id).subscribe(result => {
      this.initViewBySale(result)
    })
  }

  initViewBySale(sale:any) {

    const {id, product, buyerId, buyerName, shippingAddress, ...saleStatus} = sale

    // Sale ID
    this.saleId = id

    // Product
    this.initProduct(product)

    // Buyer from sale info
    this.profService.findById(buyerId).subscribe(result => {
      this.initBuyer(result)
    })
    // Shipping Address
    this.selectedShippingAddress = shippingAddress
    // Sale Status
    this.status = saleStatus
  }

  initViewByProductId(id:number) {
    this.prodService.findById(id).subscribe(result => {
      this.initProduct(result)
      // Buyer from security
      this.profService.findById(this.context.security?.id!).subscribe(result => {
        this.initBuyer(result)
      })

    })
  }

  initProduct(result:any) {
    this.product = result
    this.orderForm?.patchValue({product: result.id})

    this.profService.findById(this.product.sellerId).subscribe(profile => {
      this.initSeller(profile)
    })
  }

  initBuyer(result:any) {
    this.buyer = result

    this.orderForm?.patchValue({buyer: result.id})

    if(this.preorderState) {
      // Has Address
      if(result.address || result.addres.length > 0) {
        this.profService.findAddressById(result.address[0].id).subscribe(vo => this.setShippingAddress(vo))
      }
    }
  }

  setShippingAddress(vo:any, add:boolean = false) {
    this.orderForm?.patchValue({shipping: vo.id})
    this.selectedShippingAddress = vo

    if(add) {
      const {townshipName, division, divisionName, ... address} = vo
      this.buyer.address.push(address)
    }
  }

  initSeller(result:any) {
    this.seller = result
  }

  sendOrder() {
    if(this.orderForm.valid) {
      this.saleService.order(this.orderForm.value).subscribe(result => {
        this.initViewBySale(result)
      })
    }
  }

  paid() {

  }

  sendMessage() {

  }

  shipped() {

  }

  cancel() {

  }

  finished() {

  }

}
