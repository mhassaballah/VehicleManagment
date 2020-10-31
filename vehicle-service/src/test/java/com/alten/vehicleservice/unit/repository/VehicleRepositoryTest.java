package com.alten.vehicleservice.unit.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.exparity.hamcrest.date.DateMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.alten.vehicleservice.model.Vehicle;
import com.alten.vehicleservice.model.VehicleId;
import com.alten.vehicleservice.repository.VehicleRepository;
import com.alten.vehicleservice.util.Utils;



@DataJpaTest
@RunWith(SpringRunner.class)
public class VehicleRepositoryTest {
	@Autowired
    private TestEntityManager entityManager;
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Test
	public void whenFindllCustomers_thenReturnCustomers() {
	    List<Vehicle> vehicles = saveVehicles(Utils.prepareVehicles());
	    // when
		 List<Vehicle> found = vehicleRepository.findAll();
	 
	    // then
		 checkResult(vehicles, found);
	}
	@Test
	public void testFindByLastConnectionDateBetween() {
	    // given
	    List<Vehicle> vehicles = saveVehicles(Utils.
	    		prepareVehicles()
	    		);
	    // when
		Calendar minuteAgo = Calendar.getInstance();
		minuteAgo.add(Calendar.MINUTE, -1);
		 List<Vehicle> found = vehicleRepository.findByLastConnectionDateBetween(minuteAgo.getTime(),new Date());
	 
	    // then
		 checkResult(vehicles, found);
	}
	@Test
	public void testFindByLastConnectionDateBetweenAndCustomerId() {
	    // given
	    List<Vehicle> vehicles = saveVehicles(Utils.
	    		prepareVehicles()
	    		);
	    // when
		Calendar minuteAgo = Calendar.getInstance();
		minuteAgo.add(Calendar.MINUTE, -1);
		 List<Vehicle> found = vehicleRepository.findByLastConnectionDateBetweenAndCustomerId(minuteAgo.getTime(),new Date(),1l);
	 
	    // then
		 assertThat(found.get(0).getCustomerId())
	      .isEqualTo(vehicles.get(0).getCustomerId());
	    assertThat(found.get(0).getStatus())
	      .isEqualTo(vehicles.get(0).getStatus());
	   
	    assertThat(found.get(0).getRegistrationNumber())
	      .isEqualTo(vehicles.get(0).getRegistrationNumber());

	    assertThat(found.get(0).getVehicleId())
	      .isEqualTo(vehicles.get(0).getVehicleId());
	   
	    MatcherAssert.assertThat(found.get(0).getLastConnectionDate(),
	    		DateMatchers.sameInstant(vehicles.get(0).getLastConnectionDate()));
	 
	}
	private void checkResult(List<Vehicle> vehicles, List<Vehicle> found) {
		assertThat(found.get(0).getCustomerId())
	      .isEqualTo(vehicles.get(0).getCustomerId());
	    assertThat(found.get(1).getCustomerId())
	      .isEqualTo(vehicles.get(1).getCustomerId());
	    assertThat(found.get(0).getStatus())
	      .isEqualTo(vehicles.get(0).getStatus());
	    assertThat(found.get(1).getStatus())
	      .isEqualTo(vehicles.get(1).getStatus());
	    assertThat(found.get(0).getRegistrationNumber())
	      .isEqualTo(vehicles.get(0).getRegistrationNumber());
	    assertThat(found.get(1).getRegistrationNumber())
	      .isEqualTo(vehicles.get(1).getRegistrationNumber());
	    assertThat(found.get(0).getVehicleId())
	      .isEqualTo(vehicles.get(0).getVehicleId());
	    assertThat(found.get(1).getVehicleId())
	      .isEqualTo(vehicles.get(1).getVehicleId());
	    MatcherAssert.assertThat(found.get(0).getLastConnectionDate(),
	    		DateMatchers.sameInstant(vehicles.get(0).getLastConnectionDate()));
	    MatcherAssert.assertThat(found.get(1).getLastConnectionDate(),
	    		DateMatchers.sameInstant(vehicles.get(1).getLastConnectionDate()));
	    assertThat(found.size())
	      .isEqualTo(vehicles.size());
	}
	@Test
	public void testGetVehiclesByCustomerId() {
		long customerId=1;
	    // given
	    List<Vehicle> vehicles = saveVehicles(Utils.
	    		prepareVehiclesByCustomerID(customerId));
	    // when
		 List<Vehicle> found = vehicleRepository.findByCustomerId(customerId);
	 
	    // then
	    assertThat(found.get(0).getCustomerId())
	      .isEqualTo(vehicles.get(0).getCustomerId());

	    assertThat(found.get(0).getStatus())
	      .isEqualTo(vehicles.get(0).getStatus());
	    assertThat(found.get(0).getRegistrationNumber())
	      .isEqualTo(vehicles.get(0).getRegistrationNumber());
	    assertThat(found.get(0).getVehicleId())
	      .isEqualTo(vehicles.get(0).getVehicleId());
	    assertThat(found.size())
	      .isEqualTo(vehicles.size());
	}
	private List<Vehicle> saveVehicles(List<Vehicle> vehicles) {
		
	    for (Iterator<Vehicle> it = vehicles.iterator(); it.hasNext();) {
			 Vehicle vehicle = it.next();
			 entityManager.persist(vehicle);
			 entityManager.flush();
			 entityManager.clear();
	        }
		return vehicles;
	}
	@Test
	public void testFindById() {
	    // given
	    List<Vehicle> vehicles = saveVehicles(Utils.
	    		prepareVehicles());
	    // when
	    VehicleId id=new VehicleId();
	    id.setRegistrationNumber("RegistrationNumber1");
	    id.setVehicleId("VehicleId1");
		 Optional<Vehicle> found = vehicleRepository.findById(id);
	 
	    // then
	    assertThat(found.get().getCustomerId())
	      .isEqualTo(vehicles.get(0).getCustomerId());

	    assertThat(found.get().getStatus())
	      .isEqualTo(vehicles.get(0).getStatus());
	    assertThat(found.get().getRegistrationNumber())
	      .isEqualTo(vehicles.get(0).getRegistrationNumber());
	    assertThat(found.get().getVehicleId())
	      .isEqualTo(vehicles.get(0).getVehicleId());
	}
}
