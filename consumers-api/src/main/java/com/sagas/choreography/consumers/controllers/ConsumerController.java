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

import com.sagas.choreography.consumer.Consumer;
import com.sagas.choreography.consumers.services.ConsumerService;
import com.sagas.choreography.util.SQSUtil;

@RestController
@RequestMapping("/consumers")
public class ConsumerController {
private List<String> QueueList = new ArrayList<String>();
	
	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate;
	
	@Autowired
	private ConsumerService consumerService;
	
	@Value("${cloud.aws.end-point.uri}")
	private String sqsEndpoit;
	
	@Value("${consumer.config.queue}")
	private String queueUrl;
	
	
	@SqsListener(value = "order-events", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	public void onMessage(String serviceData, @Header("MessageId") String messageId, @Header("ApproximateFirstReceiveTimestamp") String approximateFirstReceiveTimestamp) {
		if (! QueueList.contains(messageId)) {
			QueueList.add(messageId);
			System.out.println("Data (Standard) = " + serviceData + " MessageId = " + messageId);
			Consumer consumer = consumerService.consumerVerified();
			SQSUtil.sendMessage(queueUrl, messageBoddy);
		}
	}
	
	@PostMapping
	public void publishConsumerVerified() {
		//TODO: Publicar la lógica de envio a la cola y sns
	}
}
