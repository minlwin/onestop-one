import { AfterViewInit, Component } from '@angular/core';
import { Router } from '@angular/router';
import { BalanceAppContext } from '../../services/balance-app.context';

declare var bootstrap:any

@Component({
  selector: 'app-message-dialog',
  templateUrl: './message-dialog.component.html',
  styles: [
  ]
})
export class MessageDialogComponent implements AfterViewInit {

  messages:string[] = []

  modalDialog:any


  ngAfterViewInit(): void {
    this.modalDialog = new bootstrap.Modal('#messageDialog', {backdrop: false})
  }

  open(
    messages:string[]
  ) {
    this.messages = messages
    this.modalDialog?.show()
  }

  close() {
    this.modalDialog?.hide()
  }
}
