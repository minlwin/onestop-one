import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter, Route } from '@angular/router';
import { AppComponent } from './app/app.component';
import { InputsComponent } from './app/inputs/inputs.component';
import { UsingListenerComponent } from './app/using-listener/using-listener.component';
import { UsingObservablesComponent } from './app/using-observables/using-observables.component';
import { UsingOutputsComponent } from './app/using-outputs/using-outputs.component';

const ROUTES:Route[] = [
  {path: 'inputs', component: InputsComponent},
  {path: 'listener', component: UsingListenerComponent},
  {path: 'observe', component: UsingObservablesComponent},
  {path: 'outputs', component: UsingOutputsComponent},
  {path: '', pathMatch: 'full', redirectTo: '/inputs'}
]

bootstrapApplication(
  AppComponent,
  {
    providers: [
      provideRouter(ROUTES)
    ]
  }
)

