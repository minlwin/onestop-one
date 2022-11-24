import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OneComponent } from './one/one.component';
import { TwoComponent } from './two/two.component';
import { OneOfOneComponent } from './one/one-of-one/one-of-one.component';
import { OneOfTwoComponent } from './one/one-of-two/one-of-two.component';

@NgModule({
  declarations: [
    AppComponent,
    OneComponent,
    TwoComponent,
    OneOfOneComponent,
    OneOfTwoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
