import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MessageDialogService } from './shared/widgets/message-dialog.service';
import { MessageDialogComponent } from './shared/widgets/message-dialog/message-dialog.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: []
})
export class AppComponent implements AfterViewInit{

  @ViewChild(MessageDialogComponent)
  messageDialog?:MessageDialogComponent

  constructor(private dialogService:MessageDialogService) {}

  ngAfterViewInit(): void {
      this.dialogService.dialog = this.messageDialog
  }
}
