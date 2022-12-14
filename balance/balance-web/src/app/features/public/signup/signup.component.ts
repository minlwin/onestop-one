import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SecurityService } from 'src/app/shared/services/security.service';

@Component({
  templateUrl: './signup.component.html',
  styles: [
  ]
})
export class SignupComponent {

  form:FormGroup
  messages:string[] = []

  constructor(builder:FormBuilder,
    private service:SecurityService,
    private router:Router
    ) {
    this.form = builder.group({
      name: ['', Validators.required],
      phone: ['', Validators.required],
      email: ['', Validators.required]
    })
  }

  signUp() {
    if(this.form.valid) {
      this.service.signUp(this.form.value).subscribe(result => {
        if(result.success) {
          this.messages = []
          this.router.navigate(['/signin'], {queryParams: {
            message: `Please sign in with passcode ${result.data}.`
          }})
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
