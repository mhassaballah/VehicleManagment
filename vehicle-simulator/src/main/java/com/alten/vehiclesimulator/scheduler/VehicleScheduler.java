package com.alten.vehiclesimulator.scheduler;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alten.vehiclesimulator.client.VehicleClient;
import com.alten.vehiclesimulator.model.Vehicle;
import com.alten.vehiclesimulator.model.VehicleId;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VehicleScheduler {
	@Autowired
	private VehicleClient vehicleClient;
	
	@Scheduled(fixedRate = 60 * 1000)
	public void pingVehicle() {
		List<Vehicle> vehicles=getAllVehicles();
		int randomVehicles = ThreadLocalRandom.current().ints(0, vehicles.size())
		.distinct().findAny().getAsInt();
		for (int v = 0; v <= randomVehicles; v++) {
			int randomVehicle=ThreadLocalRandom.current().ints(0, vehicles.size())
			.distinct().findAny().getAsInt();
			log.info("random device {}",randomVehicle);
			Vehicle vehicle=vehicles.get(randomVehicle);
			VehicleId id=new VehicleId(vehicle.getVehicleId(),
					vehicle.getRegistrationNumber());
			vehicleClient.pingVehicle(id);
		}

	}
	private List<Vehicle> getAllVehicles(){
		return vehicleClient.getAllVehicles().getBody();
	}

}
