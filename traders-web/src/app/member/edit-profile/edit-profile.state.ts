import { Injectable } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';

@Injectable()
export class EditProfileState {

  userProfile:FormGroup

  editable = false

  view = 'profile'

  loadImage = new Subject

  constructor(private builder:FormBuilder) {
    this.userProfile = builder.group({
      id:0,
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      personalInfo: builder.group({
        greeting: '',
        coverImage: '',
        dateOfBirth: '',
        gender: ''
      }),
      bankingInfo: builder.array([]),
      address: builder.array([])
    })
  }

  init(profile: any): void {
    const {personalInfo, bankingInfo, address, ... account} = profile
    this.userProfile.patchValue(account)

    if(personalInfo) {
      this.userProfile.get('personalInfo')?.patchValue(personalInfo)
    }

    if(bankingInfo) {
      this.setBankingInfo(bankingInfo)
    } else {
      this.addBankingInfo()
    }

    if(address) {
      this.setAddress(address)
    } else {
      this.addAddress()
    }
  }


  get bankingInfo() {
    return this.userProfile.get('bankingInfo') as FormArray<FormGroup>
  }

  get address() {
    return this.userProfile.get('address') as FormArray<FormGroup>
  }

  addBankingInfo() {

    this.bankingInfo.push(this.builder.group({
      id: 0,
      type: ['', Validators.required],
      accountName: ['', Validators.required],
      accountNumber: ['', Validators.required],
      deleted: false
    }))
  }

  setBankingInfo(array:Array<any>) {
    array.forEach(item => this.bankingInfo.push(
        this.builder.group({
          id: item.id,
          type: [item.type, Validators.required],
          accountName: [item.accountName, Validators.required],
          accountNumber: [item.accountNumber, Validators.required],
          deleted: item.deleted
        })
    ))
  }

  addAddress() {
    this.address.push(this.builder.group({
      id: 0,
      name: ['', Validators.required],
      address: ['', Validators.required],
      township: ['', Validators.required],
      deleted: false
    }))
  }

  setAddress(array:Array<any>) {
    array.forEach(item => this.address.push(
      this.builder.group({
        id: item.id,
        name: [item.name, Validators.required],
        address: [item.address, Validators.required],
        township: [item.township, Validators.required],
        deleted: item.deleted
      })
    ))
  }

  deleteBankingInfo(index:number) {
    let target = this.bankingInfo.at(index) as FormGroup
    if(target.get('id')?.value == 0) {
      this.bankingInfo.removeAt(index)
    } else {
      target.patchValue({deleted : true})
    }
  }

  deleteAddress(index:number) {
    let target = this.address.at(index) as FormGroup
    if(target.get('id')?.value == 0) {
      this.address.removeAt(index)
    } else {
      target.patchValue({deleted : true})
    }
  }

  set coverImage(image:string) {
    this.userProfile.get('personalInfo')?.patchValue({coverImage : image})
  }

}
