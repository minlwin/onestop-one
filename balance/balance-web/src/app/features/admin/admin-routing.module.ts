import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { CategoriesComponent } from './categories/categories.component';
import { MembersComponent } from './members/members.component';

const routes: Routes = [
  {
    path: '', component: AdminComponent,
    children: [
      {path: 'members', component: MembersComponent},
      {path: 'categories', component: CategoriesComponent},
      {path: '', pathMatch: 'full', redirectTo: 'members'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
