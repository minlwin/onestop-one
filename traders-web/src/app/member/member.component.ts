import { SecurityContext } from './../services/security/sercurity-context';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { isLayoutMetadata, LayoutMetadata, ThreeColumn } from './member-layout';

@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styles: [
  ]
})
export class MemberComponent implements OnInit {

  info:any

  layout:LayoutMetadata = new ThreeColumn

  constructor(private context:SecurityContext, private route:ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => this.info = params)
  }

  routeActivated(component:any) {
    this.layout = isLayoutMetadata(component) ? component : new ThreeColumn
  }

  get userName() {
    return this.context.security?.name
  }

  signOut() {
    this.context.signOut();
  }

}
