package com.sagas.choreography.consumers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.QueueMessageHandler;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.amazonaws.AmazonClientException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

@Configuration
public class SQSConfig {
	
	@Value("${cloud.aws.credentials.access-key}")
	private String awsAccesKey;
	
	@Value("${cloud.aws.credentials.secret-key}")
	private String awsAccessSecreteKey;
	
	@Bean
	public QueueMessagingTemplate queueMessagingTemplate () {
		AmazonSQSAsync amazonSQSAsync;
		amazonSQSAsync = amazonSQSAsync();
		
		return new QueueMessagingTemplate(amazonSQSAsync);
	}
	
	@Bean
	@Primary
	public AmazonSQSAsync amazonSQSAsync () {
        ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
        AmazonSQSAsync amazonSQSAsync;
        try {
            credentialsProvider.getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
            		"Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (~/.aws/credentials), and is in valid format.",
                    e);
        }
		
        //amazonSQSAsync = AmazonSQSAsyncClientBuilder.standard().withRegion(Regions.US_EAST_1).withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccesKey, awsAccessSecreteKey))).build();
        amazonSQSAsync = AmazonSQSAsyncClientBuilder.standard().withRegion(Regions.US_EAST_1).withCredentials(credentialsProvider).build();
        
        return amazonSQSAsync;
	}
	/*
    @Bean
    @ConfigurationProperties(prefix = "aws.configuration")
    public ClientConfiguration clientConfiguration() {
        return new ClientConfiguration();
    }
	
    @Bean
    @ConfigurationProperties(prefix = "aws.queue")
    public SimpleMessageListenerContainer simpleMessageListenerContainer(AmazonSQSAsync amazonSQSAsync) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setAmazonSqs(amazonSQSAsync);
        simpleMessageListenerContainer.setMessageHandler(queueMessageHandler());
        simpleMessageListenerContainer.setMaxNumberOfMessages(10);
        simpleMessageListenerContainer.setTaskExecutor(threadPoolTaskExecutor());
        return simpleMessageListenerContainer;
    }


    @Bean
    public QueueMessageHandler queueMessageHandler() {
        QueueMessageHandlerFactory queueMessageHandlerFactory = new QueueMessageHandlerFactory();
        queueMessageHandlerFactory.setAmazonSqs(amazonSQSAsync());
        QueueMessageHandler queueMessageHandler = queueMessageHandlerFactory.createQueueMessageHandler();
        return queueMessageHandler;
    }
    
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.setThreadNamePrefix("oaoQueueExecutor");
        executor.initialize();
        return executor;
    }
    
    @Bean
    public QueueMessagingTemplate messagingTemplate(@Autowired AmazonSQSAsync amazonSQSAsync) {
        return new QueueMessagingTemplate(amazonSQSAsync);
    }
    */
}

