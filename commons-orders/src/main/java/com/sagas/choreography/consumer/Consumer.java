package com.sagas.choreography.consumer;

import lombok.Data;

@Data
public class Consumer {

	private int id;
	private String name;
	private ConsumerState state;
	
	
}
