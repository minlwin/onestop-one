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
  target?:string[]
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

    route.queryParams.subscribe(params => this.target = params['target'])
  }

  ngOnInit(): void {
  }

  signUp() {
    if(this.form.valid) {
      this.service.signUp(this.form.value).subscribe(result => {
        if(result.success) {
          this.router.navigate(this.target || ['/'])
        } else {
          this.message = result.message
        }
      })
    }
  }

}
