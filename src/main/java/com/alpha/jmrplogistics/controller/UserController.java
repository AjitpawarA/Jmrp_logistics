package com.alpha.jmrplogistics.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alpha.jmrplogistics.dto.OrderDto;
import com.alpha.jmrplogistics.dto.ResponseStructure;
import com.alpha.jmrplogistics.entity.Order;
import com.alpha.jmrplogistics.services.OrderService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/orders")
	public ResponseEntity<ResponseStructure<Order>> addOrder(@RequestBody OrderDto order,
			@CookieValue(name = "role") String role) {
		return orderService.addOrder(order, role);
	}

	@PutMapping("/orders/orderId/{id}")
	public ResponseEntity<ResponseStructure<Order>> updateorder(@RequestBody OrderDto order,
			@PathVariable("id") int id) {
		return orderService.updateOrder(order, id);
	}
	
	
	
	
	
	
	
	@GetMapping("/getfrom3rdparty")
	public ArrayList<Object> getu() {
		String uri = "http:/localhost:8080/api/admin/getallusers";
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<Object> users = (ArrayList<Object>) restTemplate.getForObject(uri, Object.class);
		return users;
	}
}
