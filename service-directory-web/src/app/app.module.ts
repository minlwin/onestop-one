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
import { StateSearchComponent } from './widgets/state-search/state-search.component';
import { StateDetailsComponent } from './pages/locations/state-details/state-details.component';
import { StateInfoComponent } from './widgets/state-info/state-info.component';
import { StateEditComponent } from './widgets/state-edit/state-edit.component';
import { NoDataComponent } from './widgets/no-data/no-data.component';
import { DestrictEditComponent } from './widgets/destrict-edit/destrict-edit.component';
import { DistrictDetailsComponent } from './pages/locations/district-details/district-details.component';
import { StateListComponent } from './widgets/state-list/state-list.component';
import { DistrictListComponent } from './widgets/district-list/district-list.component';

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
    NoDataComponent,
    DestrictEditComponent,
    DistrictDetailsComponent,
    StateListComponent,
    DistrictListComponent
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
