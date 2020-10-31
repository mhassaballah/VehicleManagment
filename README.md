# Vehicle Tracking System



   It helps to keep track of the complete details of the vehicles .

### Task Description

------------


   - Our clients have a number of connected vehicles that belongs to a number of customers.
   - Our clients have a need to be able to view the status of the connection among  these vehicles on a monitoring display.
   - The vehicles send the status of the connection one time per minute.
   - The status can be compared with a ping (network trace); no request from the vehicle means no connection so the vehicle is either Connected or Disconnected.

### Task Analysis

------------


  - Our Task to create a data store that keeps these vehicles with their status and the customers who own them, as well as a GUI (preferably web-based) that displays the status.
  - Obviously, for this task, there are no real vehicles available that can respond to your "ping" request.
  - This can either be solved by using static values or ​​by creating a separate machinery that returns random fake status.

### Task Requirements

------------


   1. Web GUI (Single Page Application Framework/Platform).
		 - An overview of all vehicles should be visible on one page (full-screen display), together with their status.
		 - It should be able to filter, to only show vehicles for a specific customer.
		 - It should be able to filter, to only show vehicles that have a specific status.
   2. Random simulation to vehicles status sending.
   3. If database design will consume a lot of time, use data in-memory representation.
   4. Unit Testing.
   5. .NET Core, Java or any language you feel comfortable with.
   6. Complete analysis for the problem.
		 - Full architectural sketch to solution.
		 - Analysis behind the solution design, technologies....
		 - How to run your solution.
   7. Use CI (Travis, Circle, TeamCity...) to verify your code (Static analysis,..) and tests.
   8. Dockerize the whole solution.
   9. Use Microservices architecture.
		- Use any Microservices Chassis Framework.
   10. Use any free tier on any cloud platform like: - AWS, Azure or GCP

#### Optional Requirements
  1. Write an integration test.
  2. Write an automation test.
  3. Explain if it is possible to be in Serverless architecture and how?
  4. Continuous delivery to the solution to the cloud.

### Application Architecture

------------


 We are using the Spring framework, especially spring cloud stack, docker, angular and AWS features build the application.The below image shows the application architecture.
 
 We decompose the application into three layers : 
 
 1. Frontend (Angular,Nginx):-
 
		We built the frontend layer using angular and we deployed it on nginx server.
        
 2. Backend  (Spring):-
 
 		We built the backend layer using spring boot and spring cloud.
 3. Data (MySQL):-
 
 		we built the data layer using AWS RDS (MySQL), phpmyadmin for 
		data visualization and we used AWS ec2 to host phpmyadmin.
 
   ![](https://i.ibb.co/frPNPYW/Untitled-Document.png)
   
### Project Structure

------------

  1- Customer Service (main service):- manage customer details.
  
  		- It is built using Spring boot and we are using the below modules:-
  
			    Spring Data Module, Spring MVC Module, Spring Cloud Stack,Spring Jasypt
				(Encryption), Junit, Spring Test, h2 and mockito.
  2- Vehicle Service (main service):- manage vehicle details.
  
		- It is built using Spring boot and we are using the below modules:-
			    Spring Data Module, Spring MVC Module, Spring Cloud Stack,Spring Jasypt
				(Encryption), Junit, Spring Test, h2 and mockito.
  3-  Vehicle Simulator Service (main service) : generate random simulation for vehicles.
  
    		- It is built using Spring boot and we are using the below modules:-
  
			    Spring Data Module, Spring MVC Module, Spring Cloud Stack,Spring Jasypt
				(Encryption), Junit, Spring Test, h2 and mockito.
                
  4-  Config Service:- manage all configurations that used by the application and read the configuration from private github repository.
   
	- It is built using Spring boot and we are using the below modules :-
			 Spring Cloud Stack,Spring jasypt.
  5-  Registry Service: it is a registration repository that every service will register itself by the name.
  
			  - It is built using Spring boot and we are using the below modules :-
			    Spring Cloud Stack
                
  6-  Gateway Service : It is an edge server that used to route all requests to the other services.
  
  			  - It is built using Spring boot and we are using the below modules :-
			    Spring Cloud Stack
  7-  Zipkin Server:- It is tracing system to trace the request life cycle.

 			  - We built it using a docker image.
 			  
  8- Vehicle Driver :- it's an angular app that used to the connected vehicles and the customer details. 
  
				- It is built using angular and nginx.

   Notes:- 
   - Each service has its own database.
   - Each restful service has its own swagger api.
   - Each microservice applies the circuit breaker pattern through spring hystrix.
   - Unit testing and integration testing are applied on each service.
   - We are using AWS RDS (MySQL) to store the data.
   - We are using phpmyadmin for database viewer and it is running on AWS EC2.
   - We are using docker compose to run multi-container at the same time.
   - We are using sonar and code coverage plug-ins to run code analysis.
   	
   
### Build Automation

------------


We are using Travis to build the project and push the images to the cloud. we implemented the below workflow for this project:-
  ![]( https://i.ibb.co/cxvVw6C/Untitled-Document-1.png )

### Cloud Architecture

------------

We are using AWS to host our services. We are using the components below:-

		1.  RDS (MySQL) for database
		2.  EC2 for phpmyadmin 
		3.  CloudWatch for logs.
		
We applied the below workflow for this project:-
  ![]( https://d2908q01vomqb2.cloudfront.net/1b6453892473a467d07372d45eb05abc2031647a/2017/07/10/ecs-spring-microservice-containers.png )

   image source (https://aws.amazon.com)


### Serverless architecture
     - It is possible to use serverless architecture. we will use the below AWS components:-
        AWS Gateway API, AWS Lambda, AWS RDS serverless
     - We will follow the below steps:-
		1.  We will create four lambda functions (aggregator,customer,vehicle,simulator) and one api gateway.
		2.  Each lambda function has its specific function. 
		3.  Angular will call the public api gateway and we will forward the request 
			to the aggregator AWS function. 
		4.  The aggregator AWS function will call the service that needed it.
		5.  Lambda function will call RDS to retrieve the details and returns the response back
			to the aggregator.
		6.  The aggregator service will send the response back to the gateway.
	
![](https://imgdb.net/images/6562.png)
	
### How to Run:

------------

1.   Local:-

     There are two approaches:-
     
	 1- maven
		
    	    cd VehicleManagment 
            mvn clean install
    
	 2- docker-compose
	 
    	    cd VehicleManagment
    	    docker-compose up


### Important endpoints:-
        
- phpmyadmin:-

		http://54.205.34.124/phpmyadmin
       
- Travis :-

		https://travis-ci.com/github/mhassaballah/VehicleManagment
     
- Docker Hub :-

		https://hub.docker.com/repository/docker/mhassaballahserviceuser/vehicle-simulator

- Vehicle Service :-
	
		http://localhost:8091/vehicles/
      
- Customer Service :-

		http://localhost:8060/customers/
    
- Frontend URL :-

		http://localhost:4400/
