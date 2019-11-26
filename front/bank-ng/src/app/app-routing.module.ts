import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClientListComponent } from './component/client-list/client-list.component';
import { ClienteSaveComponent } from './component/cliente-save/cliente-save.component';


const routes: Routes = [
  {path:'cliente-list', component:ClientListComponent},
  {path:'cliente-save', component:ClienteSaveComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
