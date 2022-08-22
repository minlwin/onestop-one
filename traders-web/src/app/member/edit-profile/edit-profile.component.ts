import { SecurityContext } from './../../services/security/sercurity-context';
import { ProfileService } from './../../services/api/profile.service';
import { Component, OnInit } from '@angular/core';
import { EditProfileState } from './edit-profile.state';
import { LeftSideBar } from '../member-layout';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  providers:[
    EditProfileState
  ],
  styles: [
  ]
})
export class EditProfileComponent extends LeftSideBar implements OnInit {

  get readonly() {
    return !this.state.editable
  }

  constructor(
    private state:EditProfileState,
    private service:ProfileService,
    private context:SecurityContext) {
      super()
    }

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

    if(this.state.userProfile.valid) {
      // save form data
      this.service.save(this.state.userProfile.value).subscribe(result => {
        this.state.init(result)
        this.state.editable = false
      })
    }
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

  get disabledAddBankInfo() {
    return !this.state.bankingInfo.valid
  }

  get disabledAddAddress() {
    return !this.state.address.valid
  }

  get disabledSaveBtn() {
    return !this.state.userProfile.valid
  }

  uploadProfileImage(files:FileList | null) {
    if(files && files.length > 0) {
      this.service.uploadImage(files[0]).subscribe(result => {
        this.state.init(result)
      })
    }
  }

}
