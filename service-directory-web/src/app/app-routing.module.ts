import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LocationsComponent } from './pages/locations/locations.component';
import { DistrictDetailsComponent } from './pages/locations/district-details/district-details.component';
import { StateDetailsComponent } from './pages/locations/state-details/state-details.component';
import { ServiceEditComponent } from './pages/services/service-edit/service-edit.component';
import { ServicesComponent } from './pages/services/services.component';
import { SignInComponent } from './pages/sign-in/sign-in.component';
import { StateDistrictComponent } from './pages/locations/state-details/state-district/state-district.component';
import { StateTownshipComponent } from './pages/locations/state-details/state-township/state-township.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'service',
    children: [
      {path: 'list', component: ServicesComponent},
      {path: 'edit', component: ServiceEditComponent},
      {path: '', redirectTo: '/service/list', pathMatch: 'full'}
    ]
  },
  {path: 'location',
    children: [
      {path: 'states', component: LocationsComponent},
      {path: 'state-details', component: StateDetailsComponent,
        children: [
          {path: 'district', component: StateDistrictComponent},
          {path: 'township', component: StateTownshipComponent},
          {path: '', redirectTo: 'district', pathMatch: 'full'}
        ]
      },
      {path: 'district-details', component: DistrictDetailsComponent},
      {path: '', redirectTo: '/location/states', pathMatch: 'full'}
    ]
  },
  {path: 'signin', component: SignInComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
