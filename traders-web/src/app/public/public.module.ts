import { ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PublicRoutingModule } from './public-routing.module';
import { PublicComponent } from './public.component';
import { WidgetsModule } from '../widgets/widgets.module';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';


@NgModule({
  declarations: [
    PublicComponent,
    SignInComponent,
    SignUpComponent
  ],
  imports: [
    CommonModule,
    PublicRoutingModule,
    WidgetsModule,
    ReactiveFormsModule
  ]
})
export class PublicModule { }
