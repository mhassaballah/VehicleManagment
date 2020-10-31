import { Injectable } from '@angular/core';
import { Vehicle } from '../model/Vehicle';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  apiURL = '/api/vehicle';

  constructor(private httpClient: HttpClient) { }

  public getConnectedVehicles() {
    return this.httpClient.get<Vehicle[]>(`${this.apiURL}/vehicles/connected/`);
  }
  public getVehiclesByCustomerId(id: number) {
    return this.httpClient.get<Vehicle[]>(`${this.apiURL}/vehicles/` + id);
  }
  public getConnectedVehiclesByCustomerId(id: number) {
    return this.httpClient.get<Vehicle[]>(`${this.apiURL}/vehicles/connected/` + id);
  }
}
