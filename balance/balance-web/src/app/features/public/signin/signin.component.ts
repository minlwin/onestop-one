import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BalanceAppContext } from 'src/app/shared/services/balance-app.context';
import { SecurityService } from 'src/app/shared/services/security.service';

@Component({
  templateUrl: './signin.component.html'
})
export class SigninComponent {

  form:FormGroup
  messages:string[] = []

  constructor(builder:FormBuilder,
    private router:Router,
    route:ActivatedRoute,
    private service:SecurityService,
    private context:BalanceAppContext) {

    this.form = builder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    })

    route.queryParamMap.subscribe(params => {
      let message = params.get('message')
      if(message) {
        this.messages.push(message)
      }
    })
  }

  signIn() {
    this.messages = []
    if(this.form.valid) {
      this.service.signIn(this.form.value).subscribe(result => {
        if(result.success) {
          this.context.setLoginUser(result.data)
          let role:string = result.data.role
          this.router.navigate(["/", role.toLocaleLowerCase()])
        } else {
          this.messages = result.data
        }
      })
    }
  }

  isInvalid(name:string) {
    let control = this.form.get(name)

    if(control?.dirty && control.touched && !control.valid) {
      return true
    }
    return false
  }
}
