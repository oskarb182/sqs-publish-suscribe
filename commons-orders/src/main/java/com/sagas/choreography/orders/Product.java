package com.sagas.choreography.orders;

import com.sagas.choreography.consumer.Consumer;

import lombok.Data;

@Data
public class Product {
	
	private long id;
	private String description;
	private Consumer consumer;
}
