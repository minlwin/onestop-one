import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SigninComponent } from './features/public/signin/signin.component';
import { SignupComponent } from './features/public/signup/signup.component';
import { DefaultPageComponent } from './shared/widgets/default-page/default-page.component';

const routes: Routes = [
  { path: 'signin', component: SigninComponent},
  { path: 'signup', component: SignupComponent},
  { path: 'admin', loadChildren: () => import('./features/admin/admin.module').then(m => m.AdminModule) },
  { path: 'member', loadChildren: () => import('./features/member/member.module').then(m => m.MemberModule) },
  { path: '**', component: DefaultPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
