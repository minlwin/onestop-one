import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DistrictService } from 'src/app/services/district.service';

@Component({
  templateUrl: './state-district.component.html',
  styles: [
  ]
})
export class StateDistrictComponent {

  list:any[] = []

  constructor(route:ActivatedRoute, api:DistrictService) {
    route.queryParamMap.subscribe(params => {
      if(params.get('id')) {
        api.search({state: params.get('id')}).subscribe(result => this.list = result)
      }
    })
  }
}
