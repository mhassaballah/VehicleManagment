package com.alten.customerserivce.model;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Customer")
public class Customer {
	@Id
	@Column(name = "CustomerId")
	private Long customerId;
	@Column(name = "CustomerName")
	private String customerName;
	@Column(name = "CustomerAddress")
	private String customerAddress;

}
