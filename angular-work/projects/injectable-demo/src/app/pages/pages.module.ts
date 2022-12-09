import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { ChildComponent } from "./child/child.component";
import { ParentComponent } from "./parent/parent.component";

@NgModule({
    declarations: [
        ParentComponent,
        ChildComponent
    ],
    imports: [
        CommonModule
    ],
    exports: [
        ParentComponent,
        ChildComponent
    ]
})
export class PagesModule {}
