import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';
import { AppComponent } from './app/app.component';
import { EventBindingComponent } from './app/event-binding/event-binding.component';
import { InterpolationComponent } from './app/interpolation/interpolation.component';
import { AttributesComponent } from './app/one-way/attributes/attributes.component';
import { ClassesComponent } from './app/one-way/classes/classes.component';
import { OneWayComponent } from './app/one-way/one-way.component';
import { PropertiesComponent } from './app/one-way/properties/properties.component';
import { StylesComponent } from './app/one-way/styles/styles.component';
import { TwoWayComponent } from './app/two-way/two-way.component';

bootstrapApplication(AppComponent,
  {
    providers: [
      provideRouter([
        {path: 'inter', component: InterpolationComponent},
        {
          path: 'one', component: OneWayComponent,
          children: [
            {path: 'prop', component: PropertiesComponent},
            {path: 'attr', component: AttributesComponent},
            {path: 'class', component: ClassesComponent},
            {path: 'style', component: StylesComponent},
            {path: '', pathMatch: 'full', redirectTo: '/one/prop'}
          ]
        },
        {path: 'two', component: TwoWayComponent},
        {path: 'event', component: EventBindingComponent},
        {path: '', pathMatch: 'full', redirectTo: '/inter'}
      ])
    ]
  }
)
