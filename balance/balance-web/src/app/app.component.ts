import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { BalanceAppContext } from './shared/services/balance-app.context';
import { MessageDialogService } from './shared/widgets/message-dialog.service';
import { MessageDialogComponent } from './shared/widgets/message-dialog/message-dialog.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: []
})
export class AppComponent implements OnInit, AfterViewInit{

  @ViewChild(MessageDialogComponent)
  messageDialog?:MessageDialogComponent

  constructor(
    private context:BalanceAppContext,
    private router:Router,
    private dialogService:MessageDialogService
  ) {}

  ngOnInit(): void {
    this.router.navigate(["/", this.context.loginUser?.role.toLocaleLowerCase() || 'signin'])
  }

  ngAfterViewInit(): void {
      this.dialogService.dialog = this.messageDialog
  }
}
