package com.alten.vehiclesimulator.integration.scheduler;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alten.vehiclesimulator.scheduler.VehicleScheduler;



@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleSchedulerIT{
	@Autowired
	private VehicleScheduler vehicleScheduler; 
	
	@Test
    public void testPingVehicle() {
		Assertions.assertThatCode(() ->  vehicleScheduler.pingVehicle())
	    .doesNotThrowAnyException();
    }
}
