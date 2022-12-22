import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { MessageDialogComponent } from './widgets/message-dialog/message-dialog.component';
import { ChangePassComponent } from './widgets/change-pass/change-pass.component';
import { ProfileEditComponent } from './widgets/profile-edit/profile-edit.component';
import { ReactiveFormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";
import { AmountByTypePipe } from './pipes/amount-by-type.pipe';
import { ApiDatesPipe } from './pipes/api-dates.pipe';

@NgModule({
    imports: [
      CommonModule,
      ReactiveFormsModule,
      RouterModule
    ],
    declarations: [
      MessageDialogComponent,
      ChangePassComponent,
      ProfileEditComponent,
      AmountByTypePipe,
      ApiDatesPipe,
    ],
    exports: [
      MessageDialogComponent,
      ChangePassComponent,
      ProfileEditComponent,
      AmountByTypePipe,
      ApiDatesPipe,
    ]
})
export class SharedModule {}
