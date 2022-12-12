import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PublicComponent } from './domains/public/public.component';

const routes: Routes = [
  {
    path: 'admin',
    loadChildren: () => import('./domains/admin/admin.module')
  },
  {
    path: 'member',
    loadChildren: () => import('./domains/member/member.module')
  },
  {path: 'home', component: PublicComponent},
  {path: '', pathMatch: 'full', redirectTo: '/home'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
