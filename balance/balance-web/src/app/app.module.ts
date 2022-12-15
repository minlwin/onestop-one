import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ErrorHandler, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PublicModule } from './features/public/public.module';
import { ApplicationErrorHandler } from './shared/handlers/application.error.handler';
import { LoginUserHeaderInterceptor } from './shared/interceptors/login-user-header.interceptor';
import { SecurityTokenInterceptor } from './shared/interceptors/security-token.interceptor';
import { SharedModule } from './shared/shared.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    PublicModule,
    HttpClientModule,
    SharedModule
  ],
  providers: [
    {provide: ErrorHandler, useClass: ApplicationErrorHandler},
    {provide: HTTP_INTERCEPTORS, useClass: SecurityTokenInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: LoginUserHeaderInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
