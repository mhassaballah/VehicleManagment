package com.alten.vehiclesimulator.unit.scheduler;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.BDDMockito.any;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.alten.vehiclesimulator.client.VehicleClient;
import com.alten.vehiclesimulator.model.Vehicle;
import com.alten.vehiclesimulator.model.VehicleId;
import com.alten.vehiclesimulator.scheduler.VehicleScheduler;

@RunWith(SpringRunner.class)
public class VehicleSchedulerUnitTest {
	@TestConfiguration
    static class VehicleSchedulerImplTestContextConfiguration {
  
        @Bean
        public VehicleScheduler VvhicleScheduler() {
            return new VehicleScheduler();
        }
    }
@Autowired
private VehicleScheduler vehicleScheduler;
@MockBean
private VehicleClient vehicleClient;
@Before
public void setUp() {
	ResponseEntity<List<Vehicle>> vehicles= prepareVehicles();
    Mockito.when(vehicleClient.getAllVehicles())
      .thenReturn(vehicles);
}
private ResponseEntity<List<Vehicle>> prepareVehicles() {
	List<Vehicle> vehicles=new ArrayList<>();
	Vehicle vehicle=new Vehicle();
	vehicle.setCustomerId(1l);
	vehicle.setRegistrationNumber("Registration Number 1");
	vehicle.setStatus("Connected");
	vehicle.setVehicleId("Vehicle Id 1");
	vehicles.add(vehicle);
	return ResponseEntity.ok(vehicles);
}
@Test
public void testPingVehicle() {
	List<Vehicle> vehicles= prepareVehicles().getBody();
    doAnswer((arguments) -> {
        assertEquals(vehicles.get(0).getRegistrationNumber(),((VehicleId) arguments.getArgument(0)).getRegistrationNumber());
        return null;
    }).when(vehicleClient).pingVehicle(any(VehicleId.class));
    vehicleScheduler.pingVehicle();

}
}
