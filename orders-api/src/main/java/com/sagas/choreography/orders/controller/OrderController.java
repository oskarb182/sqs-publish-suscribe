package com.sagas.choreography.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sagas.choreography.orders.Order;
import com.sagas.choreography.orders.service.IOrderService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/orders/v1")
@Slf4j
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@PostMapping
	public ResponseEntity<String> createOrder(@RequestBody Order order) {

		log.info("Orden recibida: " + order);

		orderService.createOrder(order);

		HttpHeaders headers = new HttpHeaders();

		return new ResponseEntity<>("Custom header set", headers, HttpStatus.OK);

	}

}
