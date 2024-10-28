package com.codeinsight.exercise.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codeinsight.exercise.DTO.OrderInformationDTO;
import com.codeinsight.exercise.DTO.OrderItemDTO;
import com.codeinsight.exercise.entity.FoodOrder;
import com.codeinsight.exercise.service.FoodOrderService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class OrderController {

	@Autowired
	FoodOrderService foodOrderService;

	@GetMapping("/order")
	public String getOrderDetails() {
		return "Hello From Orders";
	}

	@PostMapping("/order")
	public String placeOrder(@RequestBody List<OrderItemDTO> orderItemDTO) {
		orderItemDTO.forEach(orderItem -> System.out.println(orderItem.toString()));
		foodOrderService.storeOrderDetails(orderItemDTO);
		return "Order placed Successfully";
	}

	@GetMapping("/order/{id}")
	public OrderInformationDTO putMethodName(@PathVariable String id) throws Exception{
		OrderInformationDTO orderInforamtionDTO = null;
		try {
			orderInforamtionDTO = foodOrderService.getOrderDetails(Long.parseLong(id));
		} catch (Exception exception) {
			return null;
		}
		return orderInforamtionDTO;
	}

}
