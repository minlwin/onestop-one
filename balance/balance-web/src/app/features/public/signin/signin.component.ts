import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BalanceAppContext } from 'src/app/shared/services/balance-app.context';
import { SecurityService } from 'src/app/shared/services/security.service';

@Component({
  templateUrl: './signin.component.html'
})
export class SigninComponent {

  form:FormGroup

  constructor(builder:FormBuilder,
    private router:Router,
    private service:SecurityService,
    private context:BalanceAppContext) {
    this.form = builder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    })
  }

  signIn() {
    if(this.form.valid) {
      this.service.signIn(this.form.value).subscribe(result => {
        if(result.success) {
          this.context.setLoginUser(result.data)
          this.router.navigate(["/", result.data.role])
        } else {

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
