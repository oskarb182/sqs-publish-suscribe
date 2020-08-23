package com.sagas.choreography.consumers.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sagas.choreography.consumers.services.ConsumerService;

@RestController
@RequestMapping("/consumers")
public class ConsumerController {
private List<String> QueueList = new ArrayList<String>();
	
	@Autowired
	private ConsumerService consumerService;
	

	
	
	@SqsListener(value = "order-events", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	public void onMessage(String serviceData, @Header("MessageId") String messageId, @Header("ApproximateFirstReceiveTimestamp") String approximateFirstReceiveTimestamp) {
		if (! QueueList.contains(messageId)) {
			QueueList.add(messageId);
			System.out.println("Data (Standard) = " + serviceData + " MessageId = " + messageId);
			consumerService.consumerVerified();
		}
	}
	
	@PostMapping
	public void publishConsumerVerified() {
		//TODO: Publicar la lógica de envio a la cola y sns
	}
}
