import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Customer } from '../model/customer';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  apiURL = '/api/customer';

  constructor(private httpClient: HttpClient) { }

  public getCustomers() {
    return this.httpClient.get<Customer[]>(`${this.apiURL}/customers/`);
  }
  public getCustomerById(id: number) {
    return this.httpClient.get<Customer>(`${this.apiURL}/` + id);
  }
}
