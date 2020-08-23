package com.sagas.choreography.consumer;

import lombok.Data;

@Data
public class ConsumerState {

	private ConsumerStateEnum state;
	private String description;
}
