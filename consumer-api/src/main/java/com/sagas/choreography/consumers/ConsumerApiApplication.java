package com.sagas.choreography.consumers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;

@SpringBootApplication
@EnableSqs
public class ConsumerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApiApplication.class, args);
	}

}
