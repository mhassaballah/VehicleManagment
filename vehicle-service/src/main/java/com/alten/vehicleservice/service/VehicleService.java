package com.alten.vehicleservice.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alten.vehicleservice.model.Vehicle;
import com.alten.vehicleservice.model.VehicleId;
import com.alten.vehicleservice.repository.VehicleRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VehicleService {
	@Autowired
	private VehicleRepository vehicleRepository;
	
@HystrixCommand(fallbackMethod = "getDefaultVehicles",
	    		commandProperties = {
	    			       @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000"),
	    			       @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="60")
 })
	public List<Vehicle> findAll() {
		log.info("find all vehicles ");
		return vehicleRepository.findAll();
}
@HystrixCommand(fallbackMethod = "getDefaultVehiclesByCustomerId",
commandProperties = {
	       @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000"),
	       @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="60")
})
	public List<Vehicle> getVehiclesbyCustomerId(Long customerId) {
		log.info("find all vehicles by customer id ");
		return vehicleRepository.findByCustomerId(customerId);
	}
@HystrixCommand(fallbackMethod = "pingDummyVehicle",
commandProperties = {
	       @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000"),
	       @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="60")
})
	public void pingVehicle(VehicleId id) throws NotFoundException {
		log.info("ping vehicle ");
		Optional<Vehicle> optionaVehicle=vehicleRepository.findById(id);
		if( optionaVehicle.isPresent()) {
			Vehicle vehicle=optionaVehicle.get();
			vehicle.setStatus("Connected");
			vehicle.setLastConnectionDate(new Date() );
			vehicleRepository.save(vehicle);
		}else {
         throw new NotFoundException("Vehicle not found");
		}
   }
	@HystrixCommand(fallbackMethod = "getDefaultConnectedVehicles",
			commandProperties = {
				       @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000"),
				       @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="60")
			})
	public List<Vehicle> getAllConnectedVehicles() {
		log.info("get all connected vehicles");
		Calendar minuteAgo = Calendar.getInstance();
		minuteAgo.add(Calendar.MINUTE, -1);
		return vehicleRepository
				.findByLastConnectionDateBetween(minuteAgo.getTime(), new Date());
	}
	@HystrixCommand(fallbackMethod = "getDefaultConnectedVehiclesByCustomerId",
			commandProperties = {
				       @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000"),
				       @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="60")
			})
	public List<Vehicle> getConnectedVehiclesByCustomerId(Long customerId) {
		log.info("get all connected vehicles by customer id {}",customerId);
		Calendar minuteAgo = Calendar.getInstance();
		minuteAgo.add(Calendar.MINUTE, -1);
		return vehicleRepository
				.findByLastConnectionDateBetweenAndCustomerId(minuteAgo.getTime(), new Date(),customerId);
	}
	public List<Vehicle> getDefaultVehicles() {
		log.info("get default vehicles ");
		return prepareVehicles();
	}
	
	public List<Vehicle> getDefaultConnectedVehicles() {
		log.info("get default connected vehicles ");
		return prepareVehicles();
	}
	public List<Vehicle> getDefaultConnectedVehiclesByCustomerId(Long customerId) {
		log.info("get default connected vehicles by customer id ");
		return prepareVehicles().stream().filter(v->v.getCustomerId() ==1l).collect(Collectors.toList());
	}
	public void pingDummyVehicle(VehicleId id) {
		log.info("ping dummy vehicle ");
	}
	public  List<Vehicle> getDefaultVehiclesByCustomerId(Long customerId) {
		log.info("get default connected vehicles ");
		return prepareVehicles();
	}
	private  List<Vehicle> prepareVehicles(){
	 	Vehicle vehicle1 = new Vehicle();
		vehicle1.setRegistrationNumber("RegistrationNumber1");
		vehicle1.setVehicleId("VehicleId1");
		vehicle1.setCustomerId(1L);
		vehicle1.setLastConnectionDate(new  Date());
		vehicle1.setStatus("Connected");
	    Vehicle vehicle2 = new Vehicle();
	    vehicle2.setRegistrationNumber("RegistrationNumber2");
	    vehicle2.setVehicleId("VehicleId2");
	    vehicle2.setCustomerId(2L);
	    vehicle2.setLastConnectionDate(new Date());
	    vehicle2.setStatus("Connected");
	    
	    List<Vehicle> vehicles=new ArrayList<>();
	    vehicles.add(vehicle1);
	    vehicles.add(vehicle2);
	   return vehicles;
}
}
