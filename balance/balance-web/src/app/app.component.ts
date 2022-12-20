import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BalanceAppContext } from './shared/services/balance-app.context';
import { MessageDialogService } from './shared/widgets/message-dialog.service';
import { MessageDialogComponent } from './shared/widgets/message-dialog/message-dialog.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: []
})
export class AppComponent implements AfterViewInit, AfterViewInit{

  @ViewChild(MessageDialogComponent)
  messageDialog?:MessageDialogComponent

  constructor(
    private dialogService:MessageDialogService
  ) {
  }

  ngAfterViewInit(): void {
      this.dialogService.dialog = this.messageDialog
  }
}
