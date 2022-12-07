import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { SubjectsPipe } from '../subjects.pipe';

@Component({
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, SubjectsPipe],
  templateUrl: './reactive-form.component.html',
  styles: [
  ]
})
export class ReactiveFormComponent {

  form:FormGroup
  subjectList = ['HTML', 'CSS', 'JS', 'Angular']
  list:any[] = []

  constructor(builder:FormBuilder) {
    this.form = builder.group({
      name: ['', Validators.required],
      contact: builder.group({
        phone: ['', Validators.required],
        email: ['', Validators.required]
      }),
      subjects: builder.array([
        builder.control(false),
        builder.control(false),
        builder.control(false),
        builder.control(false)
      ])
    })
  }

  get subjects() {
    return this.form.get('subjects') as FormArray
  }

  save() {
    if(this.form.valid) {
      this.list.push(this.form.value)
    }
  }

  edit(item:any) {
    this.form.patchValue(item)
  }
}
