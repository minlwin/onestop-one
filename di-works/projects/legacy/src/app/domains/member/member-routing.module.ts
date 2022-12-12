import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { ChangePassComponent } from "./change-pass.component";
import { MemberComponent } from "./member.component";
import { ProfileComponent } from "./profile.component";
import { SettingsComponent } from "./settings.component";

@NgModule({
    imports: [RouterModule.forChild([
        {
            path: '',
            component: MemberComponent,
            children:[
                {path: 'profile', component: ProfileComponent},
                {path: 'pass', component: ChangePassComponent},
                {path: 'settings', component: SettingsComponent},
                {path: '', pathMatch: 'full', redirectTo: 'profile'}
            ]
        }
    ])],
    exports: [RouterModule]
})
export default class MemberRoutingModule {

}
