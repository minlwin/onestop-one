import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';
import { AppComponent } from './app/app.component';
import { ReactiveFormComponent } from './app/reactive-form/reactive-form.component';
import { TemplateFormComponent } from './app/template-form/template-form.component';

bootstrapApplication(
  AppComponent,
  {
    providers: [
      provideRouter([
        {path: 'template', component: TemplateFormComponent},
        {path: 'reactive', component: ReactiveFormComponent},
        {path: '', pathMatch: 'full', redirectTo: '/template'}
      ])
    ]
  })
