import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CourseApi } from '../../../services/course.api';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { COURSE_LEVELS } from '../course-list/course-list.component';

@Component({
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './course-edit.component.html',
  styles: [
  ]
})
export class CourseEditComponent implements OnInit{

  form:FormGroup
  levels = COURSE_LEVELS

  constructor(builder:FormBuilder, private api:CourseApi,
      private router:Router, private route:ActivatedRoute) {
    this.form = builder.group({
      id: 0,
      name: ['', Validators.required],
      level: ['', Validators.required],
      duration: 0,
      fees: 0
    })
  }

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(params => {
      if(params.get('id')) {
        this.api.findById(params.get('id')).subscribe(result => this.form.patchValue(result))
      }
    })
  }

  save() {
    if(this.form.valid) {
      this.api.save(this.form.value).subscribe(() => {
        this.router.navigate(['/course'])
      })
    }
  }
}
