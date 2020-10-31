package com.alten.vehicleservice.integration.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.alten.vehicleservice.controller.VehicleController;
import com.alten.vehicleservice.model.VehicleId;
import com.google.gson.Gson;



@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VehicleControllerIT {
	private MockMvc mockMvc;
    

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    VehicleController vehicleController;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.vehicleController).build();
    }

    @Test
    public void testFindAllVehicles() throws Exception {
        mockMvc.perform(get("/vehicles/").contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
 	      .andExpect(jsonPath("$[0].vehicleId", is("VLUR4X20009048066")))
 	      .andExpect(jsonPath("$[0].registrationNumber", is("GHI789")))
 	      .andExpect(jsonPath("$[0].customerId").value(1L))
 	      .andExpect(jsonPath("$[1].vehicleId", is("VLUR4X20009048066")))
 	      .andExpect(jsonPath("$[1].registrationNumber", is("PQR678")))
 	      .andExpect(jsonPath("$[1].customerId").value(3L));
    }
    @Test
    public void testFindAllVehiclesByCustomerId() throws Exception {
        long customerId=2;
    	mockMvc.perform(get("/vehicles/"+customerId).contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
 	      .andExpect(jsonPath("$[0].vehicleId", is("YS2R4X20005387949")))
 	      .andExpect(jsonPath("$[0].registrationNumber", is("MNO345")));
    }
    @Test
    public void testGetAllConnectedVehicles() throws Exception {
    	mockMvc.perform(get("/vehicles/connected").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
    	.andExpect(jsonPath("$[0].vehicleId", is("VLUR4X20009048066")))
	    .andExpect(jsonPath("$[0].registrationNumber", is("GHI789")));
    }
    @Test
    public void testGetAllConnectedVehiclesByCustomerId() throws Exception {
    	mockMvc.perform(get("/vehicles/connected/"+1).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
    	.andExpect(jsonPath("$[0].vehicleId", is("VLUR4X20009048066")))
	    .andExpect(jsonPath("$[0].registrationNumber", is("GHI789")));
    }
    @Test
    public void testAPingVehicle() throws Exception {
    	VehicleId id=new VehicleId("VLUR4X20009048066", "GHI789");
    	Gson gson=new Gson();
    	mockMvc.perform(post("/vehicles/ping")
    			.content(gson.toJson(id)).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }
}
