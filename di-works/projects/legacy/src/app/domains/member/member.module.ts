import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { ChangePassComponent } from "./change-pass.component";
import MemberRoutes from "./member-routing.module";
import { MemberComponent } from "./member.component";
import { ProfileComponent } from "./profile.component";
import { SettingsComponent } from "./settings.component";

@NgModule({
    declarations: [
        ProfileComponent,
        ChangePassComponent,
        SettingsComponent,
        MemberComponent
    ],
    imports: [
        CommonModule, MemberRoutes
    ],
})
export default class MemberModule {

}
