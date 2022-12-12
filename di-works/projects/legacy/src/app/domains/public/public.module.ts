import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { PublicComponent } from "./public.component";

@NgModule({
    imports: [CommonModule],
    declarations: [PublicComponent],
    exports: [PublicComponent]
})
export class PublicModule {}
