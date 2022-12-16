import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { handleApiResult } from 'src/app/shared/services';
import { BalanceAppContext } from 'src/app/shared/services/balance-app.context';
import { ProfileService } from 'src/app/shared/services/profile.service';
import { MessageDialogService } from 'src/app/shared/widgets/message-dialog.service';

@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styles: [
  ]
})
export class MemberComponent {

  constructor(
    private context:BalanceAppContext,
    private router:Router,
    private service:ProfileService,
    private messageDialog:MessageDialogService) {
  }

  signOut() {
    this.context.logout()
    this.router.navigate(['/'])
  }

  changePassword(form:any) {
    if(form) {
      this.service.changePassword(form).subscribe(result => {
        const message = handleApiResult(result)
        this.messageDialog.show([message])
      })
    }
  }

  editProfile(form:any) {
    if(form) {
      this.service.updateProfile(form).subscribe(result => {
        this.context.setLoginUser(handleApiResult(result))
        this.router.navigate(['/'])
      })
    }
  }

  get userName() {
    return this.context.loginUser.name
  }
}
