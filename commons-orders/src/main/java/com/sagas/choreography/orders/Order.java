package com.sagas.choreography.orders;

import java.util.List;

import lombok.Data;

@Data
public class Order {
	
	private long orderId;
	private List<Product> product;
	
	
}
