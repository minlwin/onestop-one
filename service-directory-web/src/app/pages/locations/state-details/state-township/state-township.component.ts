import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TownshipService } from 'src/app/services/township.service';

@Component({
  templateUrl: './state-township.component.html',
  styles: [
  ]
})
export class StateTownshipComponent {

  list:any[] = []

  constructor(route:ActivatedRoute, api:TownshipService) {
    route.queryParamMap.subscribe(params => {
      if(params.get('id')) {
        api.search({state: params.get('id')}).subscribe(result => this.list = result)
      }
    })
  }
}
