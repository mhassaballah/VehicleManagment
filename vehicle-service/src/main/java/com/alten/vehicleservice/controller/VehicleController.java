package com.alten.vehicleservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.alten.vehicleservice.model.Vehicle;
import com.alten.vehicleservice.model.VehicleId;
import com.alten.vehicleservice.service.VehicleService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	@GetMapping(path = "/vehicles/")
	public ResponseEntity<List<Vehicle>> getAllVehicles() {
		log.info("get all vehicles");
		return ResponseEntity.ok(vehicleService.findAll());
	}
	@GetMapping(path = "/vehicles/connected")
	public ResponseEntity<List<Vehicle>> getAllConnectedVehicles() {
		log.info("get all connected vehicles");
		return ResponseEntity.ok(vehicleService.getAllConnectedVehicles());
	}
	@GetMapping(path = "/vehicles/{customerId}")
	public ResponseEntity<List<Vehicle>> getVehiclesByCustomerId(@PathVariable Long  customerId) {
		log.info("get all vehicles by customerId {}",customerId);
		return ResponseEntity.ok(vehicleService.getVehiclesbyCustomerId(customerId));
	}
	@GetMapping(path = "/vehicles/connected/{customerId}")
	public ResponseEntity<List<Vehicle>> getConnectedVehiclesByCustomerId(@PathVariable Long  customerId) {
		log.info("get all connected vehicles by customerId {}",customerId);
		return ResponseEntity.ok(vehicleService.getConnectedVehiclesByCustomerId(customerId));
	}
	@PostMapping(path = "/vehicles/ping")
	public void pingVehicle( @RequestBody VehicleId  id) {
		log.info("ping vehicle {}",id.getRegistrationNumber());
		try {
		 vehicleService.pingVehicle(id);
		}catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND,e.getMessage(), e);
		}
	}
}
