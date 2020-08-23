package com.sagas.choreography.orders.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/orders/v1")
@Slf4j
public class OrdersController {
	
	
	@PostMapping
	public void createOrder() {
		
		
		
	} 
	
}
