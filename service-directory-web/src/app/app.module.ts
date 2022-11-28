import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { LocationsComponent } from './pages/locations/locations.component';
import { SignInComponent } from './pages/sign-in/sign-in.component';
import { ServicesComponent } from './pages/services/services.component';
import { ServiceSearchBarComponent } from './pages/services/service-search-bar/service-search-bar.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ServiceEditComponent } from './pages/services/service-edit/service-edit.component';
import { StateSearchComponent } from './pages/locations/state-search/state-search.component';
import { StateDetailsComponent } from './pages/locations/state-details/state-details.component';
import { StateInfoComponent } from './pages/locations/state-details/state-info/state-info.component';
import { StateEditComponent } from './pages/locations/state-edit/state-edit.component';
import { NoDataComponent } from './widgets/no-data/no-data.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LocationsComponent,
    SignInComponent,
    ServicesComponent,
    ServiceSearchBarComponent,
    ServiceEditComponent,
    StateSearchComponent,
    StateDetailsComponent,
    StateInfoComponent,
    StateEditComponent,
    NoDataComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
