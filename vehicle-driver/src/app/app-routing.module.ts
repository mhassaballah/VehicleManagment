import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CustomersComponent } from './customer/component/customers.component';
import { VehiclesComponent } from './vehicles/component/vehicles.component';


const routes: Routes = [
  {path: 'customers' , component: CustomersComponent},
  {path: '', component: VehiclesComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
