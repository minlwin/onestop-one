import { Router } from '@angular/router';
import { SecurityContext } from './services/security/sercurity-context';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: []
})
export class AppComponent implements OnInit {

  constructor(private context:SecurityContext, private router:Router) {}

  ngOnInit(): void {
    this.router.navigate([this.context.security?.role.toLocaleLowerCase() || 'public'])
  }
}
