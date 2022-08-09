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

  constructor(private context:SecurityContext, route:ActivatedRoute) {
    route.params.subscribe(params => this.hideSideBar = params['hideSideBar'])
  }

  hideSideBar = false

  ngOnInit(): void {
  }

  get userName() {
    return this.context.security?.name
  }

  signOut() {
    this.context.signOut();
  }

}
