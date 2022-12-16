import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MemberRoutingModule } from './member-routing.module';
import { MemberComponent } from './member.component';
import { BalanceListComponent } from './balance-list/balance-list.component';
import { BalanceHomeComponent } from './balance-home/balance-home.component';
import { BalanceEditComponent } from './balance-edit/balance-edit.component';
import { BalanceDetailsComponent } from './balance-details/balance-details.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [
    MemberComponent,
    BalanceListComponent,
    BalanceHomeComponent,
    BalanceEditComponent,
    BalanceDetailsComponent
  ],
  imports: [
    CommonModule,
    MemberRoutingModule,
    SharedModule
  ]
})
export class MemberModule { }
