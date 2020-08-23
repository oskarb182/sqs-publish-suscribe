package com.sagas.choreography.util;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;


public class SQSUtil {
	
	/**
	 * Metodo utilitario para el envio de mensajes a un cola
	 * @param queueUrl
	 * @param messageBoddy
	 */
	public static void sendMessage(String queueUrl, Object object) {

		AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

		SendMessageRequest send_msg_request = new SendMessageRequest().withQueueUrl(queueUrl).withDelaySeconds(5);
		send_msg_request.withMessageBody(JsonUtil.object2Json(object));
		sqs.sendMessage(send_msg_request);

	}

}
