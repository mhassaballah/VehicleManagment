package com.alten.vehicleservice.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.exparity.hamcrest.date.DateMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.alten.vehicleservice.model.Vehicle;
import com.alten.vehicleservice.model.VehicleId;
import com.alten.vehicleservice.repository.VehicleRepository;
import com.alten.vehicleservice.service.VehicleService;
import com.alten.vehicleservice.util.Utils;

import javassist.NotFoundException;

@RunWith(SpringRunner.class)
public class VehicleSerivceTest {

	 @TestConfiguration
	    static class VehicleServiceImplTestContextConfiguration {
	  
	        @Bean
	        public VehicleService vehicleService() {
	            return new VehicleService();
	        }
	    }
	@Autowired
	private VehicleService vehicleService;
	@MockBean
	private VehicleRepository vehicleRepository ;
	
	@Before
	public void setUp() {
	    Mockito.when(vehicleRepository.findAll())
	      .thenReturn(Utils.prepareVehicles());
	    Mockito.when(vehicleRepository.findByCustomerId(1))
	      .thenReturn(Utils.prepareVehiclesByCustomerID(1));;
	    Mockito.when(vehicleRepository.
	    		findByLastConnectionDateBetween(any(Date.class), any(Date.class)))
	      .thenReturn(Utils.prepareVehicles());
	    VehicleId vehicle=new VehicleId();
		vehicle.setRegistrationNumber("RegistrationNumber1");
		vehicle.setVehicleId("VehicleId1");
	    Mockito.when(vehicleRepository.findById(vehicle))
	      .thenReturn(Optional.of(Utils.prepareVehicles().get(0)));
	    
	    Mockito.when(vehicleRepository.findByLastConnectionDateBetweenAndCustomerId(any(Date.class), any(Date.class), any(Long.class)))
	      .thenReturn(Utils.prepareVehicles());
	}
	@Test
	public void testFindByLastConnectionDateBetween() {
		 List<Vehicle> found = vehicleService.getAllConnectedVehicles();
		  checkResult(found);
	}
	@Test
	public void testFindByLastConnectionDateBetweenAndCustomerId() {
		 List<Vehicle> found = vehicleService.getAllConnectedVehicles();
		 assertThat(found.get(0).getCustomerId())
	      .isEqualTo(1L);
		 assertThat(found.get(0).getRegistrationNumber())
	      .isEqualTo("RegistrationNumber1");
		  assertThat(found.get(0).getVehicleId())
	      .isEqualTo("VehicleId1");
	}
	private void checkResult(List<Vehicle> found) {
		assertThat(found.get(0).getCustomerId())
	      .isEqualTo(1L);
	    assertThat(found.get(1).getCustomerId())
	      .isEqualTo(2L);
	    assertThat(found.get(0).getRegistrationNumber())
	      .isEqualTo("RegistrationNumber1");
	    assertThat(found.get(1).getRegistrationNumber())
	      .isEqualTo("RegistrationNumber2");
	    assertThat(found.get(0).getVehicleId())
	      .isEqualTo("VehicleId1");
	    assertThat(found.get(1).getVehicleId())
	      .isEqualTo("VehicleId2");
	    assertThat(found.get(0).getStatus())
	      .isEqualTo("Connected");
	    assertThat(found.get(1).getStatus())
	      .isEqualTo("Connected");
	    MatcherAssert.assertThat(found.get(0).getLastConnectionDate(),
	    		DateMatchers.sameOrBefore(new Date()));
	    MatcherAssert.assertThat(found.get(1).getLastConnectionDate(),
	    		DateMatchers.sameOrBefore(new Date()));
	}
	@Test
	public void testAllVehicles() {
	    List<Vehicle> found = vehicleService.findAll();
	  
		  checkResult(found);
  }
	@Test
	public void testGetVehiclesByCustomerId() {
		long customerId=1;
	    List<Vehicle> found = vehicleService.getVehiclesbyCustomerId(customerId);
	  
	    assertThat(found.get(0).getCustomerId())
	      .isEqualTo(1L);
	    assertThat(found.get(0).getRegistrationNumber())
	      .isEqualTo("RegistrationNumber1");
	    assertThat(found.get(0).getVehicleId())
	      .isEqualTo("VehicleId1");
	    assertThat(found.get(0).getStatus())
	      .isEqualTo("Connected");
  }
	@Test
	public void testPingVehicle() {
		VehicleId vehicle=new VehicleId();
		vehicle.setRegistrationNumber("RegistrationNumber1");
		vehicle.setVehicleId("VehicleId1");
		doAnswer((arguments) -> {
	        assertEquals(vehicle.getRegistrationNumber(),((Vehicle) arguments.getArgument(0)).getRegistrationNumber());
	        return null;
	    }).when(vehicleRepository).save(any(Vehicle.class));
		try {
			vehicleService.pingVehicle(vehicle);
		} catch (NotFoundException e) {
			  assertThat(e.getMessage())
		      .isEqualTo("Vehicle not found");
		}

	}
}
