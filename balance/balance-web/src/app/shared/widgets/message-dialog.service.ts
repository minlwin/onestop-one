import { Injectable } from "@angular/core";

interface MessageDoalog {
    open(messages:string[]):void
}

@Injectable({providedIn: 'root'})
export class MessageDialogService {

    dialog?:MessageDoalog

    show(messages:string[]) {
      this.dialog?.open(messages)
    }
}
