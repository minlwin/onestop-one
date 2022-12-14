import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PublicModule } from './features/public/public.module';
import { LoginUserHeaderInterceptor } from './shared/interceptors/login-user-header.interceptor';
import { SecurityTokenInterceptor } from './shared/interceptors/security-token.interceptor';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    PublicModule,
    HttpClientModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: SecurityTokenInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: LoginUserHeaderInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
