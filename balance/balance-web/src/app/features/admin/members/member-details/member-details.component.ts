import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiResult } from 'src/app/shared/services';
import { MemberService } from 'src/app/shared/services/member.service';

@Component({
  templateUrl: './member-details.component.html',
  styles: [
  ]
})
export class MemberDetailsComponent {

  dto:any
  messages:string[] = []

  constructor(route:ActivatedRoute, private service:MemberService) {
    route.queryParamMap.subscribe(params => {
      let id = params.get('id')
      if(id) {
        service.findById(+id).subscribe(result => this.handleResult(result))
      }
    })
  }

  changeRole() {
    if(this.dto?.role) {
      this.service.updateRole(this.dto.id, this.dto.role == 'Admin' ? 'Member' : 'Admin')
        .subscribe(result => this.handleResult(result))
    }
  }

  changeStatus() {
    if(this.dto?.denied) {
      this.service.updateStatus(this.dto.id, this.dto.denied ? false : true)
        .subscribe(result => this.handleResult(result))
    }
  }

  private handleResult(result:ApiResult) {
    this.messages = []
    if(result.success) {
      this.dto = result.data
    } else {
      this.messages = result.data
    }
}
}
