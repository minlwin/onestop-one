import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter, Route } from '@angular/router';
import { AppComponent } from './app/app.component';
import { CourseEditComponent } from './app/components/views/course-edit/course-edit.component';
import { CourseListComponent } from './app/components/views/course-list/course-list.component';
import { CourseApi } from './app/services/course.api';

const ROUTES:Route[] = [
  {path: 'course', component: CourseListComponent},
  {path: 'course-edit', component: CourseEditComponent},
  {path: '', pathMatch: 'full', redirectTo: '/course'}
]

bootstrapApplication(
  AppComponent,
  {
    providers: [
      provideRouter(ROUTES)
    ]
  }
)
