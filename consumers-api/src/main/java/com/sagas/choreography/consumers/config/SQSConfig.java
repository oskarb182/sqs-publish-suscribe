package com.sagas.choreography.consumers.config;

import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

@Configuration
public class SQSConfig {
	
	@Bean
	public QueueMessagingTemplate queueMessagingTemplate () {
		AmazonSQSAsync amazonSQSAsync;
		amazonSQSAsync = amazonSQSAsync();
		
		return new QueueMessagingTemplate(amazonSQSAsync);
	}
	
	@Bean
	@Primary
	public AmazonSQSAsync amazonSQSAsync () {
        return AmazonSQSAsyncClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
	}
}

