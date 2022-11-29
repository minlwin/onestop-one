import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StateService } from 'src/app/services/state.service';

declare var bootstrap:any

@Component({
  templateUrl: './locations.component.html',
  styles: [
  ]
})
export class LocationsComponent implements OnInit{

  list:any[] = []

  targetState:any

  stateEditModal:any

  constructor(private stateApi:StateService, private router:Router) {
  }

  ngOnInit(): void {
    this.stateEditModal = new bootstrap.Modal('#stateEditModal',
      {backdrop: false})
  }

  search(form:any) {
    this.stateApi.search(form)
      .subscribe(result => this.list = result)
  }

  addNewState(event:boolean) {
    if(event) {
      this.targetState = {id: 0}
      this.stateEditModal.show()
    }
  }

  save(stateForm:any) {
    this.stateApi.save(stateForm).subscribe(result => {
      this.router.navigate(['/location', 'state-details'], {queryParams: {id: result.id}})
    })
  }

  upload(file:any) {
    if(file) {
      this.stateApi.upload(file).subscribe(result => {
        this.list = result
      })
    }
  }
}
