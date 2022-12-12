import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AdminHomeComponent } from "./home/home.component";
import { MastersComponent } from "./masters/masters.component";
import { MemberComponent } from "./members/members.component";

export const ADMIN_ROUTES:Routes = [
    {path: 'home', component: AdminHomeComponent},
    {path: 'members', component: MemberComponent},
    {path: 'masters', component: MastersComponent}
]

@NgModule({
    imports: [RouterModule.forChild(ADMIN_ROUTES)],
    exports: [RouterModule]
})
export default class AdminRoutingModule {}
