import { SecurityContext } from './../../services/security/sercurity-context';
import { ProfileService } from './../../services/api/profile.service';
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

  constructor(
    private state:EditProfileState,
    private service:ProfileService,
    private context:SecurityContext) { }

  ngOnInit(): void {
    this.service.findById(this.context.security?.id!).subscribe(
      profile => this.state.init(profile)
    )
  }

  get userProfile() {
    return this.state.userProfile
  }

  get view() {
    return this.state.view
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
    this.state.loadImage.next("Load Imaeg")
  }

  get disabledAddBankInfo() {
    return !this.state.bankingInfo.valid
  }

}
