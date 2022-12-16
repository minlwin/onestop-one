import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { MembersComponent } from './members/members.component';
import { CategoriesComponent } from './categories/categories.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MemberDetailsComponent } from './members/member-details/member-details.component';
import { CategoryEditComponent } from './categories/category-edit/category-edit.component';


@NgModule({
  declarations: [
    AdminComponent,
    MembersComponent,
    CategoriesComponent,
    MemberDetailsComponent,
    CategoryEditComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    ReactiveFormsModule
  ]
})
export class AdminModule { }
