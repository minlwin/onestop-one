import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { MembersComponent } from './members/members.component';
import { CategoriesComponent } from './categories/categories.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MemberDetailsComponent } from './members/member-details/member-details.component';


@NgModule({
  declarations: [
    AdminComponent,
    MembersComponent,
    CategoriesComponent,
    MemberDetailsComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    ReactiveFormsModule
  ]
})
export class AdminModule { }
