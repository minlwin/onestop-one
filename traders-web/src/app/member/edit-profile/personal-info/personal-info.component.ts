import { ProfileService } from './../../../services/api/profile.service';
import { EditProfileState } from './../edit-profile.state';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

@Component({
  templateUrl: './personal-info.component.html',
  styles: [
  ]
})
export class PersonalInfoComponent implements OnInit {

  @ViewChild("photo")
  imageInput?:ElementRef

  constructor(
    private state:EditProfileState,
    private profileService:ProfileService) {
    state.view = 'profile'
  }

  ngOnInit(): void {
  }

  get form() {
    return this.state.userProfile
  }

  get readonly() {
    return !this.state.editable
  }

  get coverPhoto() {
    return this.state.userProfile.get('personalInfo')?.get('coverImage')?.value
  }

}
