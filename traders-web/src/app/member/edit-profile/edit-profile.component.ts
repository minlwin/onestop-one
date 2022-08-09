import { Component, OnInit } from '@angular/core';
import { EditProfileState } from './edit-profile.state';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  providers:[
    EditProfileState
  ],
  styles: [
  ]
})
export class EditProfileComponent implements OnInit {

  get readonly() {
    return !this.state.editable
  }

  constructor(private state:EditProfileState) { }

  get userProfile() {
    return this.state.userProfile
  }

  get view() {
    return this.state.view
  }

  ngOnInit(): void {
  }


  save() {
    // save form data

    // reload view
  }

  edit() {
    this.state.editable = !this.state.editable
  }

  addBankingInfo() {
    this.state.addBankingInfo()
  }

  addAddress() {
    this.state.addAddress()
  }

  uploadProfileImage() {

  }

  get disabledAddBankInfo() {
    return !this.state.bankingInfo.valid
  }

}
