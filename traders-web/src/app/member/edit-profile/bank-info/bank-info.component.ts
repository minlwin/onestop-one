import { EditProfileState } from './../edit-profile.state';
import { Component, OnInit } from '@angular/core';

@Component({
  templateUrl: './bank-info.component.html',
  styles: [
  ]
})
export class BankInfoComponent implements OnInit {

  constructor(private state:EditProfileState) {
    state.view = 'bank'
  }

  ngOnInit(): void {
  }

  get bankInfo() {
    return this.state.bankingInfo
  }

  get readonly() {
    return !this.state.editable
  }

  removeItem(index:number) {
    this.state.deleteBankingInfo(index)
  }

}
