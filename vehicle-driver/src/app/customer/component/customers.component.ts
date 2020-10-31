import { Component, OnInit, OnDestroy } from '@angular/core';
import { Customer } from '../model/customer';
import { Subject } from 'rxjs';
import { CustomerService } from '../service/customer.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit , OnDestroy {

  constructor(private customerService: CustomerService) {}


  dtOptions: DataTables.Settings = {};
  customers: Customer [] = [];
  dtTrigger: Subject<any> = new Subject();

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      processing: true
    };
    this.customerService.getCustomers().subscribe(data => {
      this.customers = data;
      this.dtTrigger.next();
    });
  }
  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }
}
