import { SecurityService } from './../../services/security/security-service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  templateUrl: './sign-up.component.html',
  styles: [
  ]
})
export class SignUpComponent implements OnInit {

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
      name: ["", [Validators.required]],
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

  get queryString() {
    return {
      route: this.route ? JSON.stringify(this.route) : '',
      query: this.query ? JSON.stringify(this.query) : ''
    }
  }

  ngOnInit(): void {
  }

  signUp() {
    if(this.form.valid) {
      this.service.signUp(this.form.value).subscribe(result => {
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
