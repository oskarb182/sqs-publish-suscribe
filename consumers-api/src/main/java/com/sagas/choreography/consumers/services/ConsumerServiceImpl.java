package com.sagas.choreography.consumers.services;

import com.sagas.choreography.consumer.Consumer;
import com.sagas.choreography.consumer.ConsumerState;
import com.sagas.choreography.consumer.ConsumerStateEnum;

public class ConsumerServiceImpl implements ConsumerService {

	@Override
	public Consumer consumerVerified() {
		
		Consumer consumer= new Consumer();
		consumer.setName("Juan perez");
		
		ConsumerState consumerState = new ConsumerState();
		consumerState.setState(ConsumerStateEnum.APPROVED);
		consumerState.setDescription("usuario aprobado");
		consumer.setState(consumerState);
		return consumer;
	}

}
