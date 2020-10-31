package com.alten.vehiclesimulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
//@EnableEurekaClient
@RibbonClient(name="vehicle-service")
@EnableHystrixDashboard
@EnableCircuitBreaker
public class VehicleSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleSimulatorApplication.class, args);
	}

}
