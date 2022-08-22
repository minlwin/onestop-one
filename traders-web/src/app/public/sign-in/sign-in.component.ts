import { Router, ActivatedRoute } from '@angular/router';
import { SecurityService } from './../../services/security/security-service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  templateUrl: './sign-in.component.html',
  styles: [
  ]
})
export class SignInComponent implements OnInit {

  form:FormGroup
  route?:string[]
  query?:any
  message?:string

  constructor(
    builder:FormBuilder,
    route:ActivatedRoute,
    private service:SecurityService,
    private router:Router) {

    this.form = builder.group({
      email: ["", [Validators.required, Validators.email]],
      password: ["", [Validators.required, Validators.minLength(8)]]
    })

    route.queryParams.subscribe(params => {
      if(params['route']) {
        this.route = JSON.parse(params['route'])
      }

      if(params['query']) {
        this.query = JSON.parse(params['query'])
      }
    })
  }

  ngOnInit(): void {
  }

  get queryString() {
    return {
      route: this.route ? JSON.stringify(this.route) : '',
      query: this.query ? JSON.stringify(this.query) : ''
    }
  }

  signIn() {
    if(this.form.valid) {
      this.service.signIn(this.form.value).subscribe((result) => {
        if(result.success) {
          if(this.route) {
            this.router.navigate(this.route, {queryParams: this.query})
          } else {
            this.router.navigate(['/'])
          }
        } else {
          this.message = result.message
        }
      })
    }
  }

}
