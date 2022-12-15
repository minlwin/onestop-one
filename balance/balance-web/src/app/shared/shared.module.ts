import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { MessageDialogComponent } from './widgets/message-dialog/message-dialog.component';

@NgModule({
    imports: [
      CommonModule
    ],
    declarations: [
      MessageDialogComponent
    ],
    exports: [
      MessageDialogComponent
    ]
})
export class SharedModule {}
