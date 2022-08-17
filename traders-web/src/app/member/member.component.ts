import { SecurityContext } from './../services/security/sercurity-context';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styles: [
  ]
})
export class MemberComponent implements OnInit {

  info:any

  constructor(private context:SecurityContext, private route:ActivatedRoute) {
    route.params.subscribe(params => this.hideSideBar = params['hideSideBar'])
  }

  hideSideBar = false

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => this.info = params)
  }

  get userName() {
    return this.context.security?.name
  }

  signOut() {
    this.context.signOut();
  }

}
