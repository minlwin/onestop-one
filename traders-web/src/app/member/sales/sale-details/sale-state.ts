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
  bankInfoForm:FormGroup

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

      this.bankInfoForm = builder.group({
        id: 0,
        type: ['', Validators.required],
        accountName: ['', Validators.required],
        accountNumber: ['', Validators.required],
        deleted: false
      })

  }

  clearBankingForm() {
    this.bankInfoForm?.patchValue({
      id: 0,
      type: '',
      accountName: '',
      accountNumber: '',
      deleted : false
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

    this.saleId = sale.id

    this.initProduct(sale.product)

    // Buyer from sale info
    this.profService.findById(sale.buyerId).subscribe(result => {
      this.initBuyer(result)
    })

    this.selectedShippingAddress = sale.shippingAddress
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


  addBankingInfo() {
    if(this.bankInfoForm?.valid) {
      this.profService.addBanking(this.seller.id, this.bankInfoForm?.value)
        .subscribe(result => this.initSeller(result))
    }
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
