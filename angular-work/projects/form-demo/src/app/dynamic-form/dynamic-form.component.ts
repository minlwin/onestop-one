import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './dynamic-form.component.html',
  styles: [
  ]
})
export class DynamicFormComponent {

  form:FormGroup

  constructor(private builder:FormBuilder) {
    this.form = builder.group({
      name: '',
      subjects: builder.array([
        builder.control('')
      ])
    })
  }

  get subjects() {
    return this.form.get('subjects') as FormArray
  }

  addNewSubject() {
    this.subjects.push(this.builder.control(''))
  }

  removeSubject(index:number) {
    this.subjects.removeAt(index)
  }
}
