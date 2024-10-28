package com.codeinsight.exercise.service;

import java.util.List;

import com.codeinsight.exercise.DTO.OrderInformationDTO;
import com.codeinsight.exercise.DTO.OrderItemDTO;

public interface FoodOrderService {
	void storeOrderDetails(List<OrderItemDTO> orderItemDTO);

	OrderInformationDTO getOrderDetails(long orderId) throws Exception;
}
