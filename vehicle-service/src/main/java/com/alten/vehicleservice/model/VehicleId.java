package com.alten.vehicleservice.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleId implements Serializable{
    private static final long serialVersionUID = 1L;
	private String vehicleId;
	 private String registrationNumber;
}
