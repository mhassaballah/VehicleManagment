package com.alten.vehicleservice.integration.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alten.vehicleservice.model.Vehicle;
import com.alten.vehicleservice.model.VehicleId;
import com.alten.vehicleservice.service.VehicleService;



@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VehicleServiceIT {

	@Autowired
	private VehicleService vehicleService; 
	@Test
    public void testFindAllVehicles() {
           List<Vehicle> allVehicles = vehicleService.findAll();
           assertThat(allVehicles).isNotNull().isNotEmpty();
           assertThat(allVehicles.get(0).getRegistrationNumber())
 	      .isEqualTo("GHI789");
           assertThat(allVehicles.get(0).getVehicleId())
  	      .isEqualTo("VLUR4X20009048066");
    }
	@Test
    public void testGetAllVehiclesByCustomerId() {
		   long customerId=3; 
           List<Vehicle> allVehicles = vehicleService.getVehiclesbyCustomerId(customerId);
           assertThat(allVehicles).isNotNull().isNotEmpty();
           assertThat(allVehicles.get(0).getRegistrationNumber())
 	      .isEqualTo("PQR678");
           assertThat(allVehicles.get(0).getVehicleId())
  	      .isEqualTo("VLUR4X20009048066");
    }
	@Test
    public void testGetAllConnectedVehicles() {
			List<Vehicle> allVehicles = vehicleService.getAllConnectedVehicles();
           assertThat(allVehicles).isNotNull().isNotEmpty();
           assertThat(allVehicles.get(0).getRegistrationNumber())
 	      .isEqualTo("GHI789");
           assertThat(allVehicles.get(0).getVehicleId())
  	      .isEqualTo("VLUR4X20009048066");
    }
	@Test
    public void testGetAllConnectedVehiclesByCustomerId() {
			List<Vehicle> allVehicles = vehicleService.getConnectedVehiclesByCustomerId(1l);
           assertThat(allVehicles).isNotNull().isNotEmpty();
           assertThat(allVehicles.get(0).getRegistrationNumber())
 	      .isEqualTo("GHI789");
           assertThat(allVehicles.get(0).getVehicleId())
  	      .isEqualTo("VLUR4X20009048066");
    }
	@Test
    public void testApingVehicle() {
    	VehicleId id=new VehicleId("VLUR4X20009048066", "GHI789");
           Assertions.assertThatCode(() -> vehicleService.pingVehicle(id))
   	    .doesNotThrowAnyException();
    }
}
