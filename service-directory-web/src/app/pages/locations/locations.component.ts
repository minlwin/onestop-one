import { Component } from '@angular/core';
import { tap } from 'rxjs';
import { StateService } from 'src/app/services/state.service';

@Component({
  templateUrl: './locations.component.html',
  styles: [
  ]
})
export class LocationsComponent {

  list:any[] = []

  constructor(private stateApi:StateService) {

  }

  search(form:any) {
    this.stateApi.search(form)
      .subscribe(result => this.list = result)
  }
}
