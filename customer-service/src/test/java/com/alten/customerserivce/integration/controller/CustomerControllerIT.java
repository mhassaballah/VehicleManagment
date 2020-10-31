package com.alten.customerserivce.integration.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.alten.customerserivce.controller.CustomerController;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerControllerIT {
	private MockMvc mockMvc;
    

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    CustomerController customerController;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.customerController).build();
    }

    @Test
    @Profile("IntegrationTest")
    public void testFindAllCustomers() throws Exception {
        mockMvc.perform(get("/customers/").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
 	       .andExpect(jsonPath("$[0].customerId").value(1L))
 	      .andExpect(jsonPath("$[0].customerName", is("Kalles Grustransporter")))
 	      .andExpect(jsonPath("$[1].customerId").value(2L))
 	      .andExpect(jsonPath("$[1].customerName", is("Johans Bulk")));
    }
    @Test
    public void testGetCustomerById() throws Exception {
        mockMvc.perform(get("/"+1).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
 	       .andExpect(jsonPath("$.customerId").value(1L))
 	      .andExpect(jsonPath("$.customerName", is("Kalles Grustransporter")));
    }
}
