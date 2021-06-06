import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PricelistComponent } from './components/pricelist/pricelist.component';

const routes: Routes = [
  {path : 'home', component: PricelistComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [PricelistComponent]