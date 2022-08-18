import { ProfileService } from './../../../services/api/profile.service';
import { EditProfileState } from './../edit-profile.state';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { PhotoService } from 'src/app/services/api/photo.service';

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
    this.state.loadImage.subscribe(_ => {
      this.imageInput?.nativeElement.click()
    })
  }

  uploadProfileImage(files:FileList | null) {
    if(files && files.length > 0) {
      this.profileService.uploadImage(files[0]).subscribe(result => {
        this.state.init(result)
      })
    }
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
