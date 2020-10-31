package com.alten.vehicleservice.unit.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.alten.vehicleservice.controller.VehicleController;
import com.alten.vehicleservice.model.Vehicle;
import com.alten.vehicleservice.model.VehicleId;
import com.alten.vehicleservice.service.VehicleService;
import com.alten.vehicleservice.util.Utils;



@RunWith(SpringRunner.class)
@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@MockBean
    private VehicleService vehicleService;
	
	@Test
	public void shouldGetAllVehicles()
	  throws Exception {
	     
	   
	    List<Vehicle> vehicles =Utils.prepareVehicles();
	 
	    given(vehicleService.findAll()).willReturn(vehicles);
	    checkgetVehiclesResponse(vehicles,"/vehicles/");

	}
	@Test
	public void testGetAllConnectedVehicles()
	  throws Exception {
	    List<Vehicle> vehicles =Utils.prepareVehicles();
	    given(vehicleService.getAllConnectedVehicles()).willReturn(vehicles);
	    checkgetVehiclesResponse(vehicles,"/vehicles/connected");

	   
	}
	@Test
	public void testGetAllConnectedVehiclesByCustomerId()
	  throws Exception {
	    List<Vehicle> vehicles =Utils.prepareVehicles();
	    given(vehicleService.getConnectedVehiclesByCustomerId(1l)).willReturn(vehicles);
	    checkgetVehiclesResponse(vehicles,"/vehicles/connected/"+1);

	   
	}
	private void checkgetVehiclesResponse(List<Vehicle> vehicles,String path) throws Exception {
		mvc.perform(get(path))
	      .andExpect(status().isOk())
	       .andExpect(jsonPath("$[0].vehicleId").value(vehicles.get(0).getVehicleId()))
	      .andExpect(jsonPath("$[0].registrationNumber", is(vehicles.get(0).getRegistrationNumber())))
	      .andExpect(jsonPath("$[0].status", is(vehicles.get(0).getStatus())))
	      .andExpect(jsonPath("$[0].customerId").value(vehicles.get(0).getCustomerId()));
	}
	@Test
	public void testGetVehiclesBycustomerId()
	  throws Exception {
	     
	   long customerId=2;
	    List<Vehicle> vehicles =Utils.prepareVehiclesByCustomerID(customerId);
	 
	    given(vehicleService.getVehiclesbyCustomerId(customerId)).willReturn(vehicles);
	    checkgetVehiclesResponse(vehicles,"/vehicles/"+customerId);

	   
	}
	@Test
	public void testPingVehicle() throws Exception {
		VehicleId vehicle=new VehicleId();
		vehicle.setRegistrationNumber("Registration Number 1");
		vehicle.setVehicleId("Vehicle Id 1");
		doAnswer((arguments) -> {
	        assertEquals(vehicle.getRegistrationNumber(),((VehicleId) arguments.getArgument(0)).getRegistrationNumber());
	        return null;
	    }).when(vehicleService).pingVehicle(any(VehicleId.class));
		vehicleService.pingVehicle(vehicle);

	}
}
