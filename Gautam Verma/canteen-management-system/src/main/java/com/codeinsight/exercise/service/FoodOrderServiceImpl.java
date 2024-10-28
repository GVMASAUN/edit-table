package com.codeinsight.exercise.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeinsight.exercise.DTO.OrderInformationDTO;
import com.codeinsight.exercise.DTO.OrderItemDTO;
import com.codeinsight.exercise.entity.FoodItem;
import com.codeinsight.exercise.entity.FoodOrder;
import com.codeinsight.exercise.entity.OrderDetails;
import com.codeinsight.exercise.entity.User;
import com.codeinsight.exercise.repository.FoodItemRepository;
import com.codeinsight.exercise.repository.FoodOrderRepository;
import com.codeinsight.exercise.repository.UserRepository;

@Service
public class FoodOrderServiceImpl implements FoodOrderService{
	
	@Autowired
	private FoodItemRepository foodItemRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FoodOrderRepository foodOrderRepository;

	@Override
	public void storeOrderDetails(List<OrderItemDTO> orderItemDTO) {
		Set<OrderDetails> orderDetails = new HashSet<OrderDetails>();
		float totalPrice = 0;
		for(OrderItemDTO orderItem: orderItemDTO) {
			FoodItem foodItem = foodItemRepository.getReferenceById(orderItem.getItemId());
			OrderDetails orderDetail = new OrderDetails();
			orderDetail.setFoodItem(foodItem);
			orderDetail.setQuantity(orderItem.getQuantity());
			orderDetail.setTotalPrice(foodItem.getItemPrice() * orderItem.getQuantity());
			totalPrice += orderDetail.getTotalPrice();
			orderDetails.add(orderDetail);
		};
		
		User user = userRepository.getReferenceById(1L);
		FoodOrder foodOrder = new FoodOrder();
		foodOrder.setDate(new Date());
		foodOrder.setOrderDetails(orderDetails);
		foodOrder.setPrice(totalPrice);
		foodOrder.setUser(user);
		
		saveFoodOrder(foodOrder);
	}
	
	private void saveFoodOrder(FoodOrder foodOrder) {
		foodOrderRepository.save(foodOrder);
	}
	
	private OrderInformationDTO transferToOrderInformationDTO(FoodOrder foodOrder) {
		OrderInformationDTO orderInformationDTO = new OrderInformationDTO();
		orderInformationDTO.setEmail(foodOrder.getUser().getEmail());
		orderInformationDTO.setName(foodOrder.getUser().getName());
		orderInformationDTO.setUserId(foodOrder.getUser().getId());
		orderInformationDTO.setTotalBill(foodOrder.getPrice());
		orderInformationDTO.setOrderDate(foodOrder.getDate());
		orderInformationDTO.setOrderDetails(foodOrder.getOrderDetails());
		
		return orderInformationDTO;
	}

	@Override
	public OrderInformationDTO getOrderDetails(long orderId) throws Exception{
		FoodOrder foodOrder = foodOrderRepository.getReferenceById(orderId);
		
		if(foodOrder == null) {
			throw new Exception("No Food Order Exists with this id");
		}
		
		return transferToOrderInformationDTO(foodOrder);
	}

}
