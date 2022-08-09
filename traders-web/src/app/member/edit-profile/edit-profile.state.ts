import { Injectable } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Injectable()
export class EditProfileState {

  userProfile:FormGroup

  editable = false

  view = 'profile'

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

    this.addBankingInfo()
    this.addAddress()
  }

  get bankingInfo() {
    return this.userProfile.get('bankingInfo') as FormArray<FormGroup>
  }

  get address() {
    return this.userProfile.get('address') as FormArray<FormGroup>
  }

  deleteBankingInfo(index:number) {
    let target = this.bankingInfo.at(index) as FormGroup
    if(target.get('id')?.value == 0) {
      this.bankingInfo.removeAt(index)
    } else {
      target.patchValue({deleted : true})
    }
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

  deleteAddress(index:number) {
    let target = this.address.at(index) as FormGroup
    if(target.get('id')?.value == 0) {
      this.address.removeAt(index)
    } else {
      target.patchValue({deleted : true})
    }
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
}
