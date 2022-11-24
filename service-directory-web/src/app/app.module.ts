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

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LocationsComponent,
    SignInComponent,
    ServicesComponent,
    ServiceSearchBarComponent,
    ServiceEditComponent
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
