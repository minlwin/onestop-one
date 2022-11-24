import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OneOfOneComponent } from './one/one-of-one/one-of-one.component';
import { OneOfTwoComponent } from './one/one-of-two/one-of-two.component';
import { OneComponent } from './one/one.component';
import { TwoComponent } from './two/two.component';

const routes: Routes = [
  {path: 'one', component: OneComponent,
    children: [
      {path: 'one', component: OneOfOneComponent},
      {path: 'two', component: OneOfTwoComponent},
      {path: '', redirectTo: '/one/one', pathMatch: 'full'}
    ]
  },
  {path: 'two', component: TwoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
