package com.sagas.choreography.consumers.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sagas.choreography.consumers.services.ConsumerService;
import com.sagas.choreography.orders.Order;
import com.sagas.choreography.util.JsonUtil;

@RestController
@RequestMapping("/consumers")
public class ConsumerController {

	@Autowired
	private ConsumerService consumerService;
	
//	@Value("${order.config.queue}")
//	private String sqsEndpoit;

	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate;
	
	@SqsListener(value = "order-events", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	public void onMessage(String serviceData, @Header("MessageId") String messageId, @Header("ApproximateFirstReceiveTimestamp") String approximateFirstReceiveTimestamp) {
		
		System.out.println("Data (Standard) = " + serviceData + " MessageId = " + messageId);
		Order order = (Order) JsonUtil.json2Object(serviceData, Order.class);
		consumerService.consumerVerified(order);
	}
	
	@PostMapping
	public void publishConsumerVerified() {
		//TODO: Publicar la lógica de envio a la cola y sns
	}
}
