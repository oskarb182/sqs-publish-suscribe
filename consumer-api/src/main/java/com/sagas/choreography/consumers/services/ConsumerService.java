package com.sagas.choreography.consumers.services;

import com.sagas.choreography.consumer.Consumer;
import com.sagas.choreography.orders.Order;

public interface ConsumerService {

	public Consumer consumerVerified(Order order);
	
}
