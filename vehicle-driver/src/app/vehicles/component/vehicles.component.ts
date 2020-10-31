import { Component, OnInit, OnDestroy } from '@angular/core';
import { VehicleService } from '../service/vehicle.service';
import { Vehicle } from '../model/Vehicle';
import { Subject, Observable, Subscription } from 'rxjs';
import { interval } from 'rxjs';
import { CustomerService } from 'src/app/customer/service/customer.service';
import { Customer } from 'src/app/customer/model/customer';

@Component({
  selector: 'app-vehicles',
  templateUrl: './vehicles.component.html',
  styleUrls: ['./vehicles.component.scss'],
  providers: [CustomerService]
})

export class VehiclesComponent implements OnInit , OnDestroy {
  vehiclesSubscription: Subscription;
  customersSubscription: Subscription;
  timerSubscription: Subscription;
  constructor(private vehicleService: VehicleService,
              private customerService: CustomerService) { }

  dtOptions: DataTables.Settings = {};
  vehicles: Vehicle [] = [];
  customers: Customer [] = [];

  dtTrigger: Subject<any> = new Subject();
  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      processing: true
    };
    this.loadConnectedVehicles(-1, () => { this.dtTrigger.next(); } );
    this.customerService.getCustomers().subscribe(data => {
      this.customers = data;
    });
  }

  loadConnectedVehicles(customerId: number, callback?: any): void {
   this.unsubscribe();
   if (Number(customerId) === -1) {
    this.vehiclesSubscription = this.vehicleService.getConnectedVehicles().subscribe(data => {
      this.vehicles = data;
      if (callback) {
        callback();
      }
      });
   } else {
    this.vehiclesSubscription = this.vehicleService.getConnectedVehiclesByCustomerId(customerId).subscribe(data => {
      this.vehicles = data;
      if (callback) {
        callback();
      }
      });
   }
   this.subscribeToData(customerId);
  }
  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
    this.unsubscribe();
    if ( this.customersSubscription ) {
    this.customersSubscription.unsubscribe();
    }
  }
  private subscribeToData(customerId: number): void {
    this.timerSubscription = interval(1 * 60000).subscribe(() => this.loadConnectedVehicles(customerId));
}
public selectCustomer(event: any) {
  const customerId: number = event.target.value;
  this.loadConnectedVehicles(customerId);

}
private unsubscribe() {
  if ( this.vehiclesSubscription ) {
    this.vehiclesSubscription.unsubscribe();
  }
  if ( this.timerSubscription ) {
   this.timerSubscription.unsubscribe();
  }
  }
}
