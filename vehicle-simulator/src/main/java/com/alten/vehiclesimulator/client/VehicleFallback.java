package com.alten.vehiclesimulator.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.alten.vehiclesimulator.model.Vehicle;
import com.alten.vehiclesimulator.model.VehicleId;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VehicleFallback implements VehicleClient {

	@Override
	public ResponseEntity<List<Vehicle>> getAllVehicles() {
		List<Vehicle> vehicles=new ArrayList<>();
		Vehicle vehicle=new Vehicle();
		vehicle.setCustomerId(1l);
		vehicle.setRegistrationNumber("Registration Number 1");
		vehicle.setStatus("Connected");
		vehicle.setVehicleId("Vehicle Id 1");
		vehicle.setLastConnectionDate(new Date());
		vehicles.add(vehicle);
		return ResponseEntity.ok(vehicles);
	}

	@Override
	public void pingVehicle(VehicleId id) {
		log.info("Done");
	}

}
