package com.sagas.choreography.consumers.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sagas.choreography.consumer.Consumer;
import com.sagas.choreography.consumer.ConsumerState;
import com.sagas.choreography.consumer.ConsumerStateEnum;
import com.sagas.choreography.util.SQSUtil;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	@Value("${consumer.config.queue}")
	private String queueUrl;
	
	public Consumer consumerVerified() {
		
		Consumer consumer= new Consumer();
		consumer.setName("Juan perez");
		
		ConsumerState consumerState = new ConsumerState();
		consumerState.setState(ConsumerStateEnum.APPROVED);
		consumerState.setDescription("usuario aprobado");
		consumer.setState(consumerState);
		SQSUtil.sendMessage(queueUrl, consumer);
		System.out.println("Mensaje enviado = " + consumer.getName());
		return consumer;
	}

}
