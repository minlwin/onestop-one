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
import { StateSearchComponent } from './utils/widgets/state-search/state-search.component';
import { StateDetailsComponent } from './pages/locations/state-details/state-details.component';
import { StateInfoComponent } from './utils/widgets/state-info/state-info.component';
import { StateEditComponent } from './utils/widgets/state-edit/state-edit.component';
import { NoDataComponent } from './utils/widgets/no-data/no-data.component';
import { DestrictEditComponent } from './utils/widgets/destrict-edit/destrict-edit.component';
import { DistrictDetailsComponent } from './pages/locations/district-details/district-details.component';
import { StateListComponent } from './utils/widgets/state-list/state-list.component';
import { DistrictListComponent } from './utils/widgets/district-list/district-list.component';
import { DistrictInfoComponent } from './utils/widgets/district-info/district-info.component';
import { TownshipListComponent } from './utils/widgets/township-list/township-list.component';
import { TownshipEditComponent } from './utils/widgets/township-edit/township-edit.component';
import { StateDistrictComponent } from './pages/locations/state-details/state-district/state-district.component';
import { StateTownshipComponent } from './pages/locations/state-details/state-township/state-township.component';
import { StatusPipe } from './utils/pipes/status.pipe';

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
    DistrictListComponent,
    DistrictInfoComponent,
    TownshipListComponent,
    TownshipEditComponent,
    StateDistrictComponent,
    StateTownshipComponent,
    StatusPipe
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
