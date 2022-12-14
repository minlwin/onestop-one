import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BalanceAppContext } from 'src/app/shared/services/balance-app.context';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styles: [
  ]
})
export class AdminComponent {

  constructor(private context:BalanceAppContext, private router:Router) {}

  signOut() {
    this.context.logout()
    this.router.navigate(['/'])
  }
}
