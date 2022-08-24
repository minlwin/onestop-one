import { isLayoutMetadata, LayoutMetadata, ThreeColumn } from './../member/member-layout';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-public',
  templateUrl: './public.component.html',
  styles: [
  ]
})
export class PublicComponent implements OnInit {

  info:any
  layout:LayoutMetadata = new ThreeColumn

  constructor(private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(result => this.info = result)
  }

  changeRoute(component:any) {
    this.layout = isLayoutMetadata(component) ? component : new ThreeColumn
  }

}
