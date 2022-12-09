import { Injectable } from "@angular/core";

@Injectable({providedIn: 'platform'})
export class MessageHolder {
    private _list:string[] = []

    get list():ReadonlyArray<string> {
        return this._list;
    }

    add(value:string) {
        if(value) {
            this._list.unshift(value)
        }
    }
}
