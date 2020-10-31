package com.alten.vehiclesimulator.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.alten.vehiclesimulator.model.Vehicle;
import com.alten.vehiclesimulator.model.VehicleId;

@FeignClient(name="vehicle-service", url = "${vehicle-gateway-url}" ,fallback = VehicleFallback.class )
public interface VehicleClient {
	@GetMapping(path = "/vehicles/")
	public ResponseEntity<List<Vehicle>> getAllVehicles();
	@PostMapping(path = "/vehicles/ping")
	public void pingVehicle( VehicleId  id);
}
