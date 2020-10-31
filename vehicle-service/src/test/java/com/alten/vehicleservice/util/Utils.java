package com.alten.vehicleservice.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.alten.vehicleservice.model.Vehicle;

public class Utils {
	 public static List<Vehicle> prepareVehicles(){
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
	public static List<Vehicle> prepareVehiclesByCustomerID(long customerId) {
		List<Vehicle> allVehicles=prepareVehicles();
		return allVehicles.stream().filter(v->v.getCustomerId()==customerId).collect(Collectors.toList());
	}
}
