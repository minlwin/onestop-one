import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { MessageDialogComponent } from './widgets/message-dialog/message-dialog.component';
import { ChangePassComponent } from './widgets/change-pass/change-pass.component';
import { ProfileEditComponent } from './widgets/profile-edit/profile-edit.component';
import { ReactiveFormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";

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
    ],
    exports: [
      MessageDialogComponent,
      ChangePassComponent,
      ProfileEditComponent,
    ]
})
export class SharedModule {}
