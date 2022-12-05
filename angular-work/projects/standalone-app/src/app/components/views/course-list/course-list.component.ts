import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Course } from '../../../services/model';
import { CourseApi } from '../../../services/course.api';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

export const COURSE_LEVELS = ['Basic', 'Intermediate', 'Advance']

@Component({
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './course-list.component.html',
  styles: [
    `.btn-wrapper {
        padding-top: 2rem;
    }`
  ]
})
export class CourseListComponent implements OnInit{

  list:Course[] = []
  form:FormGroup
  levels = COURSE_LEVELS

  constructor(private api: CourseApi, builder:FormBuilder) {
    this.form = builder.group({
      level: '',
      name: ''
    })
  }

  ngOnInit(): void {
    this.search()
  }

  search() {
    this.api.search(this.form.value).subscribe(result => this.list = result)
  }

}
