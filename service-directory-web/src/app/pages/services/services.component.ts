import { Component } from '@angular/core';
import { StateService } from 'src/app/services/state.service';

@Component({
  templateUrl: './services.component.html',
})
export class ServicesComponent {

  states:any[] = []

  constructor(private stateApi:StateService) {}

  search(form:any) {
    console.log(form)
  }

  loadStateData(region:string) {
    this.states = []

    if(region) {
      this.stateApi.search({region: region, deleted: false})
        .subscribe(result => this.states = result)
    }
  }
}
