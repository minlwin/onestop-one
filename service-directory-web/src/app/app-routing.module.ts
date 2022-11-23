import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LocationsComponent } from './pages/locations/locations.component';
import { ServiceEditComponent } from './pages/services/service-edit/service-edit.component';
import { ServicesComponent } from './pages/services/services.component';
import { SignInComponent } from './pages/sign-in/sign-in.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'service', component: ServicesComponent},
  {path: 'service-edit', component: ServiceEditComponent},
  {path: 'location', component: LocationsComponent},
  {path: 'signin', component: SignInComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
