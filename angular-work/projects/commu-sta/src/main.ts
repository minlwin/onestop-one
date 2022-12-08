import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';
import { AppComponent } from './app/app.component';
import { ParentComponent } from './app/parent/parent.component';

bootstrapApplication(AppComponent, {providers: [
  provideRouter([
    {path: 'outs', component: ParentComponent},
    {path: '', pathMatch: 'full', redirectTo: '/outs'}
  ])
]})
