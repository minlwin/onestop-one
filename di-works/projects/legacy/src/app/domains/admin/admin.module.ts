import { NgModule } from "@angular/core";
import AdminRoutingModule from "./admin-routing.module";
import { AdminHomeComponent } from "./home/home.component";
import { MastersComponent } from "./masters/masters.component";
import { MemberComponent } from "./members/members.component";

@NgModule({
    declarations: [
        AdminHomeComponent,
        MastersComponent,
        MemberComponent
    ],
    imports: [
        AdminRoutingModule
    ],
})
export default class AdminModule {}
