package com.sagas.choreography.consumers.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sqs")
public class SQSController {
	private List<String> QueueList = new ArrayList<String>();
	
	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate;
	
	@Value("${cloud.aws.end-point.uri}")
	private String sqsEndpoit;
	
	
	@SqsListener(value = "order-events", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	public void onMessage(String serviceData, @Header("MessageId") String messageId, @Header("ApproximateFirstReceiveTimestamp") String approximateFirstReceiveTimestamp) {
		if (! QueueList.contains(messageId)) {
			
			QueueList.add(messageId);
			System.out.println("Data (Standard) = " + serviceData + " MessageId = " + messageId);
		}
	}
	
//	@SqsListener(value = "WUE1-AT-CRT-PT-SQS-01.fifo", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
//	public void getMessage(String serviceData, @Header("MessageId") String messageId, @Header("ApproximateFirstReceiveTimestamp") String approximateFirstReceiveTimestamp) {
//		if (! QueueList.contains(messageId)) {
//			QueueList.add(messageId);
//			System.out.println("Data (Fifo) = " + serviceData + " MessageId = " + messageId);
//		}
//	}
}

