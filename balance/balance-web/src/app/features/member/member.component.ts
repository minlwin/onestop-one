import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BalanceAppContext } from 'src/app/shared/services/balance-app.context';

@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styles: [
  ]
})
export class MemberComponent {

  constructor(private context:BalanceAppContext, private router:Router) {

  }

  signOut() {
    this.context.logout()
    this.router.navigate(['/'])
  }

  get userName() {
    return this.context.loginUser.name
  }
}
