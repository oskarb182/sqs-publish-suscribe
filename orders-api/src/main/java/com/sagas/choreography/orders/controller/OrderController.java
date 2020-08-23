package com.sagas.choreography.orders.controller;


import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sagas.choreography.orders.Order;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/orders/v1")
@Slf4j
public class OrderController {
	
	
	@PostMapping
	public void createOrder(@RequestBody Order order ) {
		
		
		log.info("Orden recibida: " + order );
		
		
		
		
	} 
	
}
