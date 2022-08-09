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
  target?:string[]

  constructor(
    builder:FormBuilder,
    route:ActivatedRoute,
    private service:SecurityService,
    private router:Router) {
    this.form = builder.group({
        email: ["", [Validators.required, Validators.email]],
        password: ["", [Validators.required, Validators.minLength(8)]]
      })

    route.queryParams.subscribe(params => this.target = params['target'])
  }

  ngOnInit(): void {
  }

  signIn() {
    if(this.form.valid) {
      this.service.signIn(this.form.value)
      this.router.navigate(this.target || ['/'])
    }
  }


}
