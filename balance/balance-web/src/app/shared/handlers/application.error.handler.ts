import { HttpErrorResponse, HttpStatusCode } from "@angular/common/http";
import { ErrorHandler, Injectable, NgZone } from "@angular/core";
import { Router } from "@angular/router";
import { ApplcationError } from "../services";
import { BalanceAppContext } from "../services/balance-app.context";
import { MessageDialogService } from "../widgets/message-dialog.service";

@Injectable()
export class ApplicationErrorHandler implements ErrorHandler{

    constructor(
      private dialog:MessageDialogService,
      private context:BalanceAppContext,
      private zone:NgZone,
      private router:Router) {}

    handleError(error: any): void {

        let messages:string[] = []

        if(error instanceof HttpErrorResponse) {

          let message = "Server Errors!. Please contact to development team."

          // Server Errors
          if(error.status == HttpStatusCode.Forbidden) {
            // Handle 403
            message = "Authentication error. Please login again."
          }

          // Logout
          this.context.logout()

          // Navigate to Sign In Page with message as query param
          this.zone.run(() => this.router.navigate(['/signin'], {queryParams: {message: message}}))
          return
        }

        if(error instanceof ApplcationError) {
            // Handle Error from API Result
            messages = error.messages
        } else if(error.message) {
            // Error with message
            messages.push(error.message)
        } else {
            // Other errors
            console.log(error)
            messages.push('Unknown error. Please contact to development team.')
        }

        this.zone.run(() => this.dialog.show(messages))
    }

}
