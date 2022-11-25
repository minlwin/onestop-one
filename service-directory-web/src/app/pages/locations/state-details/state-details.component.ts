import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StateService } from 'src/app/services/state.service';

@Component({
  templateUrl: './state-details.component.html',
  styles: [
  ]
})
export class StateDetailsComponent {

  targetState:any

  constructor(route:ActivatedRoute, stateApi:StateService) {
    route.queryParamMap.subscribe(params => {
      const id = params.get('id')
      stateApi.findById(id).subscribe(result => this.targetState = result)
    })
  }
}
