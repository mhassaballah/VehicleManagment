package com.alten.vehicleservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "Vehicle")
@IdClass(VehicleId.class)
public class Vehicle {
	@Id
	@Column(name = "VehicleId")
	private String vehicleId;
	@Id
	@Column(name = "RegistrationNumber")
	private String registrationNumber;
	@Column(name = "Status")
	private String status;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LastConnectionDate")
	private Date lastConnectionDate;
	@Column(name = "CustomerId")
	private Long customerId;
}
