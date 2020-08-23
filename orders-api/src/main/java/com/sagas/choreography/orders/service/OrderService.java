package com.sagas.choreography.orders.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sagas.choreography.orders.Order;
import com.sagas.choreography.util.SQSUtil;

import lombok.extern.java.Log;

@Service
@Log
public class OrderService implements IOrderService {
	
	@Value( "${order.config.queue}" )
	private String urlQueue;
	
	
	@Override
	public void createOrder(Order order) {
	
		log.info("Orden recibida:" + order);
		
		SQSUtil.sendMessage(urlQueue, order);
		
	}

}
