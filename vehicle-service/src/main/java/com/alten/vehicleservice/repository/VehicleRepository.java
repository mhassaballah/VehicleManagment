package com.alten.vehicleservice.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alten.vehicleservice.model.Vehicle;
import com.alten.vehicleservice.model.VehicleId;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, VehicleId> {
	public List<Vehicle> findByCustomerId(long customerId);

	public List<Vehicle> findByLastConnectionDateBetween(Date from, Date to);
	public List<Vehicle> findByLastConnectionDateBetweenAndCustomerId(Date from, Date to,Long id);

}
