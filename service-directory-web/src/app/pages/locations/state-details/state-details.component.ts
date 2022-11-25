import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StateService } from 'src/app/services/state.service';

declare var bootstrap:any

@Component({
  templateUrl: './state-details.component.html',
  styles: [
  ]
})
export class StateDetailsComponent implements OnInit{

  targetState:any

  editModal:any

  constructor(route:ActivatedRoute, private stateApi:StateService) {
    route.queryParamMap.subscribe(params => {
      const id = params.get('id')
      stateApi.findById(id).subscribe(result => this.targetState = result)
    })
  }

  ngOnInit(): void {
    this.editModal = new bootstrap.Modal('#stateEditModal', {backdrop: false})
  }

  edit(event:boolean) {
    if(event) {
      this.editModal.show()
    }
  }

  save(form:any) {
    this.stateApi.save(form).subscribe(result => this.targetState = result)
  }
}
