package com.alten.vehiclesimulator.model;


import java.util.Date;

import lombok.Data;

@Data
public class Vehicle {
	private String vehicleId;
	private String registrationNumber;
	private String status;
	private Long customerId;
	private Date lastConnectionDate;

}
