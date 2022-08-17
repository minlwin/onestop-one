import { Router } from '@angular/router';
import { SecurityService } from './../../services/security/security-service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  templateUrl: './change-pass.component.html',
  styles: [
  ]
})
export class ChangePassComponent implements OnInit {

  form:FormGroup

  constructor(
    builder:FormBuilder,
    private router:Router,
    private service:SecurityService) {
    this.form = builder.group({
      oldPass: ['', [Validators.required, Validators.minLength(8)]],
      newPass: ['', [Validators.required, Validators.minLength(8)]],
    })
  }

  ngOnInit(): void {
  }

  changePass() {
    if(this.form.valid) {
      this.service.changePass(this.form.value).subscribe(result => {
        this.router.navigate(['/member'], {queryParams: result});
      })
    }
  }

}
