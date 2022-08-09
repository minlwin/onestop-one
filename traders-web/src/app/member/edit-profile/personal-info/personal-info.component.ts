import { EditProfileState } from './../edit-profile.state';
import { Component, OnInit } from '@angular/core';

@Component({
  templateUrl: './personal-info.component.html',
  styles: [
  ]
})
export class PersonalInfoComponent implements OnInit {

  constructor(private state:EditProfileState) {
    state.view = 'profile'
  }

  ngOnInit(): void {
  }

}
