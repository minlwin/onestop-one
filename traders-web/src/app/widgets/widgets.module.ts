import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoryListComponent } from './components/category-list/category-list.component';
import { TopSellersComponent } from './components/top-sellers/top-sellers.component';
import { CompactNumberPipe } from './pipes/compact-number.pipe';
import { ProductsComponent } from './pages/products/products.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ProductItemComponent } from './components/product-item/product-item.component';
import { ImgOneComponent } from './components/product-item/img-one/img-one.component';
import { ImgTwoComponent } from './components/product-item/img-two/img-two.component';
import { ImgThreeComponent } from './components/product-item/img-three/img-three.component';
import { ImgFourComponent } from './components/product-item/img-four/img-four.component';
import { DateToDurationPipe } from './pipes/date-to-duration.pipe';
import { ImageHostDirective } from './components/product-item/image-host.directive';
import { BackgroundImagePipe } from './pipes/background-image.pipe';
import { ProductImageComponent } from './components/product-image/product-image.component';
import { ProductDetailsComponent } from './pages/product-details/product-details.component';
import { MemberSideMenuComponent } from './components/member-side-menu/member-side-menu.component';



@NgModule({
  declarations: [
    CategoryListComponent,
    TopSellersComponent,
    CompactNumberPipe,
    ProductsComponent,
    ProductItemComponent,
    ImgOneComponent,
    ImgTwoComponent,
    ImgThreeComponent,
    ImgFourComponent,
    DateToDurationPipe,
    ImageHostDirective,
    BackgroundImagePipe,
    ProductImageComponent,
    ProductDetailsComponent,
    MemberSideMenuComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule
  ],
  exports: [
    CategoryListComponent,
    TopSellersComponent,
    CompactNumberPipe,
    DateToDurationPipe,
    BackgroundImagePipe,
    MemberSideMenuComponent
  ]
})
export class WidgetsModule { }
