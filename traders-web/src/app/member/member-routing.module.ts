import { MessageDetailsComponent } from './messages/message-details/message-details.component';
import { ProductPhotosComponent } from './my-products/product-edit/product-photos/product-photos.component';
import { ProductFeaturesComponent } from './my-products/product-edit/product-features/product-features.component';
import { ProductInfoComponent } from './my-products/product-edit/product-info/product-info.component';
import { ProductEditComponent } from './my-products/product-edit/product-edit.component';
import { MyProductsComponent } from './my-products/my-products.component';
import { ProductDetailsComponent } from './../widgets/pages/product-details/product-details.component';
import { ProductsComponent } from './../widgets/pages/products/products.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MemberComponent } from './member.component';
import { ChangePassComponent } from './change-pass/change-pass.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { PersonalInfoComponent } from './edit-profile/personal-info/personal-info.component';
import { BankInfoComponent } from './edit-profile/bank-info/bank-info.component';
import { ShippingInfoComponent } from './edit-profile/shipping-info/shipping-info.component';
import { ProductListComponent } from './my-products/product-list/product-list.component';
import { MessageListComponent } from './messages/message-list/message-list.component';
import { SaleListComponent } from './sales/sale-list/sale-list.component';
import { SaleDetailsComponent } from './sales/sale-details/sale-details.component';

const routes: Routes = [
  {
    path: '',
    component: MemberComponent ,
    children: [
      {
        path: 'profile',
        children: [
          {path: 'edit', component: EditProfileComponent, children: [
            {path: 'personal', component: PersonalInfoComponent},
            {path: 'bank', component: BankInfoComponent},
            {path: 'shipping', component: ShippingInfoComponent},
            {path: '', redirectTo: 'personal', pathMatch: 'full'}
          ]},
          {path: 'products', component: MyProductsComponent, children: [
            {path: 'list', component: ProductListComponent},
            {path: 'edit', component: ProductEditComponent, children: [
              {path: 'info', component: ProductInfoComponent},
              {path: 'features', component: ProductFeaturesComponent},
              {path: 'photos', component: ProductPhotosComponent},
              {path: '', redirectTo: 'info', pathMatch: 'full'}
            ]},
            {path: '', redirectTo: 'list', pathMatch: 'full'}
          ]},
          {path: 'changepass', component: ChangePassComponent}
        ]
      },
      {path: 'products/details/:id', component: ProductDetailsComponent},
      {path: 'products', component: ProductsComponent},
      {path: 'message', children: [
        {path: 'list/:type', component: MessageListComponent},
        {path: 'details', component: MessageDetailsComponent},
      ]},
      {path: 'sale', children: [
        {path: 'list/:type', component: SaleListComponent},
        {path: 'details', component: SaleDetailsComponent},
      ]},
      {path: '', redirectTo: 'products', pathMatch: 'prefix'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MemberRoutingModule { }
