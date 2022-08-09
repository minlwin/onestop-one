import { SignUpComponent } from './sign-up/sign-up.component';
import { ProductDetailsComponent } from './../widgets/pages/product-details/product-details.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductsComponent } from '../widgets/pages/products/products.component';
import { PublicComponent } from './public.component';
import { SignInComponent } from './sign-in/sign-in.component';

const routes: Routes = [
  { path: '', component: PublicComponent, children: [
    {path: 'signin', component: SignInComponent},
    {path: 'signup', component: SignUpComponent},
    {path: 'products/details/:id', component: ProductDetailsComponent},
    {path: 'products', component: ProductsComponent},
    {path: '', redirectTo: 'products', pathMatch: 'full'}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PublicRoutingModule { }
