import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { DataTablesModule } from 'angular-datatables';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';
import { NavComponent } from './nav/nav.component';
import { CustomersComponent } from './customer/component/customers.component';
import { VehiclesComponent } from './vehicles/component/vehicles.component';
import { CustomerService } from './customer/service/customer.service';
import {MatSelectModule} from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDividerModule} from '@angular/material/divider';



@NgModule({
  declarations: [
    AppComponent,
    CustomersComponent,
    NavComponent,
    VehiclesComponent
    ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    AppRoutingModule,
    DataTablesModule,
    HttpClientModule,
    MatSelectModule,
    MatFormFieldModule,
    MatDividerModule
  ],
  providers: [CustomerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
