import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';
import { AppComponent } from './app/app.component';
import { InputsLocalComponent } from './app/inputs-local/inputs-local.component';
import { InputsViewComponent } from './app/inputs-view/inputs-view.component';
import { InputsComponent } from './app/inputs/inputs.component';
import { UsingListenerComponent } from './app/using-listener/using-listener.component';
import { UsingObservableComponent } from './app/using-observable/using-observable.component';
import { UsingOutputsComponent } from './app/using-outputs/using-outputs.component';

bootstrapApplication(
  AppComponent, {providers: [
    provideRouter([
      {path: 'inputs', component: InputsComponent},
      {path: 'local', component: InputsLocalComponent},
      {path: 'views', component: InputsViewComponent},
      {path: 'listener', component: UsingListenerComponent},
      {path: 'observable', component: UsingObservableComponent},
      {path: 'outs', component: UsingOutputsComponent},
      {path: '', pathMatch: 'full', redirectTo: '/inputs'}
    ])
  ]}
)

