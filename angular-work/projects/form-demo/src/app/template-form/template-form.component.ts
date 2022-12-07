import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './template-form.component.html',
  styles: [
  ]
})
export class TemplateFormComponent {

  studentModel:any

  subjectList = ['HTML', 'CSS', 'JS', 'Angular']

  list:any[] = []

  constructor() {
    this.initForm()
  }

  save(form:NgForm) {
    const{subjects, ...formValue} = form.value

    formValue.subjects = Object.entries(subjects)
      .filter(entry => entry[1])
      .map(entry => entry[0])
      .map(str => Number.parseInt(str))
      .map(index => this.subjectList[index])

    this.list.push(formValue)

    this.initForm()
  }

  edit(item:any) {
    const {subjects, ...form} = item
    this.studentModel = form
    this.studentModel.subjects = {}

    const array = subjects as string[]

    for(let i = 0; i < this.subjectList.length; i ++) {
      const item = this.subjectList[i]
      this.studentModel.subjects['' + i] = array.filter(a => a == item).length > 0
    }
  }

  private initForm() {
    this.studentModel = {
      employeeName: '',
      contact: {
        phone: '',
        email: ''
      },
      subjects: {
        "0": '',
        "1": '',
        "2": '',
        "3": ''
      }
    }
  }


}
