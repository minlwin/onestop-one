import { AfterViewInit, Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

declare const bootstrap:any

@Component({
  selector: 'app-category-edit',
  templateUrl: './category-edit.component.html',
  styles: [
  ]
})
export class CategoryEditComponent implements AfterViewInit {

  @Output()
  onSaveListener = new EventEmitter

  form:FormGroup
  modal:any

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      id: 0,
      type: ['', Validators.required],
      name: ['', Validators.required]
    })
  }

  ngAfterViewInit(): void {
    this.modal = new bootstrap.Modal('#categoryEdit', {backdrop: false})
  }

  show(data:any) {
    this.form.patchValue(data || {
      id: 0,
      type: '',
      name: ''
    })

    this.modal?.show()
  }

  save() {
    if(this.form.valid) {
      this.onSaveListener.emit(this.form.value)
      this.modal?.hide()
    }
  }

  hasError(name:string) {
    return this.form.get(name)?.dirty && this.form.get('name')?.touched && !this.form.get(name)?.valid
  }
}
