import { Component } from "@angular/core";

@Component({
    template: `
        <div class="row">
            <div class="col-4">
                <div class="list-group">
                    <a [routerLink]="['profile']" routerLinkActive="active"  class="list-group-item">Profile</a>
                    <a [routerLink]="['pass']" routerLinkActive="active"  class="list-group-item">Change Password</a>
                    <a [routerLink]="['settings']" routerLinkActive="active"  class="list-group-item">Settings</a>
                </div>
            </div>
            <div class="col">
                <router-outlet></router-outlet>
            </div>
        </div>
    `
})
export class MemberComponent {

}
