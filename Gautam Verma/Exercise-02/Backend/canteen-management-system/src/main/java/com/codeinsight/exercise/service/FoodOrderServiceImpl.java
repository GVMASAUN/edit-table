package com.codeinsight.exercise.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.codeinsight.exercise.DTO.GenericResponseDTO;
import com.codeinsight.exercise.DTO.OrderDTO;
import com.codeinsight.exercise.DTO.OrderItemDTO;
import com.codeinsight.exercise.entity.FoodItem;
import com.codeinsight.exercise.entity.FoodOrder;
import com.codeinsight.exercise.entity.OrderDetails;
import com.codeinsight.exercise.entity.User;
import com.codeinsight.exercise.repository.FoodItemRepository;
import com.codeinsight.exercise.repository.FoodOrderRepository;
import com.codeinsight.exercise.repository.UserRepository;

@Service
public class FoodOrderServiceImpl implements FoodOrderService {

	@Autowired
	private FoodItemRepository foodItemRepository;
	@Autowired
	private FoodOrderRepository foodOrderRepository;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private UserRepository userRepository;

	@Override
	public GenericResponseDTO<OrderDTO> updateOrder(List<OrderItemDTO> orderItemDTO, Long orderId) {
		GenericResponseDTO<OrderDTO> responseDTO = new GenericResponseDTO<OrderDTO>();

		try {
			Set<OrderDetails> orderDetails = createOrderDetails(orderItemDTO);
			float totalPrice = calculateTotalPrice(orderDetails);

			FoodOrder foodOrder = foodOrderRepository.getReferenceById(orderId);
			foodOrder.setDate(new Date());
			foodOrder.getOrderDetails().clear();
			foodOrder.getOrderDetails().addAll(orderDetails);
			foodOrder.setPrice(totalPrice);

			saveFoodOrder(foodOrder);

			responseDTO.setStatusCode(HttpStatus.OK.value());
			responseDTO.setMessage("Order Updated successfully");
		} catch (Exception exception) {
			responseDTO.setError("Error updating order: " + exception.getMessage());
			responseDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return responseDTO;
	}

	@Override
	public GenericResponseDTO<OrderDTO> storeOrderDetails(List<OrderItemDTO> orderItemDTO) {
		GenericResponseDTO<OrderDTO> responseDTO = new GenericResponseDTO<OrderDTO>();

		try {
			Set<OrderDetails> orderDetails = createOrderDetails(orderItemDTO);
			float totalPrice = calculateTotalPrice(orderDetails);

			User user = userService.getCurrentUser();
			FoodOrder foodOrder = new FoodOrder();
			foodOrder.setDate(new Date());
			foodOrder.setOrderDetails(orderDetails);
			foodOrder.setPrice(totalPrice);
			foodOrder.setUser(user);

			saveFoodOrder(foodOrder);

			responseDTO.setStatusCode(HttpStatus.OK.value());
			responseDTO.setMessage("Order placed successfully");
		} catch (Exception exception) {
			responseDTO.setError("Error placing order: " + exception.getMessage());
			responseDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return responseDTO;
	}

	private Set<OrderDetails> createOrderDetails(List<OrderItemDTO> orderItemDTO) {
		Set<OrderDetails> orderDetails = new HashSet<>();
		for (OrderItemDTO orderItem : orderItemDTO) {
			FoodItem foodItem = foodItemRepository.getReferenceById(orderItem.getItemId());
			OrderDetails orderDetail = new OrderDetails();
			orderDetail.setFoodItem(foodItem);
			orderDetail.setQuantity(orderItem.getQuantity());
			orderDetail.setTotalPrice(foodItem.getItemPrice() * orderItem.getQuantity());
			orderDetails.add(orderDetail);
		}
		return orderDetails;
	}

	private float calculateTotalPrice(Set<OrderDetails> orderDetails) {
		return orderDetails.stream().map(OrderDetails::getTotalPrice).reduce(0f, Float::sum);
	}

	private void saveFoodOrder(FoodOrder foodOrder) {
		foodOrderRepository.save(foodOrder);
	}

	@Override
	public GenericResponseDTO<OrderDTO> getOrderDetails(long orderId) {
		GenericResponseDTO<OrderDTO> responseDTO = new GenericResponseDTO<OrderDTO>();
		
		try {
			FoodOrder foodOrder = foodOrderRepository.getReferenceById(orderId);
			OrderDTO orderDTO = populateOrderDTOFromFoodOrder(foodOrder);
			
			responseDTO.setData(orderDTO);
			
			responseDTO.setStatusCode(HttpStatus.OK.value());
			responseDTO.setMessage("Order Fetched Successfully");
			
		} catch (Exception exception) {
			responseDTO.setError("Error fetching orders: " + exception.getMessage());
			responseDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		
		return responseDTO;
	}

	@Override
	public GenericResponseDTO<List<OrderDTO>> getCurrentUserOrders() {
		GenericResponseDTO<List<OrderDTO>> responseDTO = new GenericResponseDTO<List<OrderDTO>>();
		List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();

		try {
			User user = userService.getCurrentUser();
			User fullUser = userRepository.getReferenceById(user.getId());

			fullUser.getFoodOrders().forEach(order -> {
				OrderDTO orderDTO = populateOrderDTOFromFoodOrder(order);
				ordersDTO.add(orderDTO);
			});

			responseDTO.setData(ordersDTO);

			responseDTO.setStatusCode(HttpStatus.OK.value());
			responseDTO.setMessage("Orders Fetched Successfully");
		} catch (Exception exception) {
			responseDTO.setError("Error fetching orders: " + exception.getMessage());
			responseDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return responseDTO;
	}

	@Override
	public GenericResponseDTO<List<OrderDTO>> getAllOrders() {
		GenericResponseDTO<List<OrderDTO>> responseDTO = new GenericResponseDTO<List<OrderDTO>>();
		List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();

		try {
			List<FoodOrder> foodOrders = foodOrderRepository.findAll();

			foodOrders.forEach(foodOrder -> {
				OrderDTO orderDTO = populateOrderDTOFromFoodOrder(foodOrder);
				ordersDTO.add(orderDTO);
			});

			responseDTO.setData(ordersDTO);

			responseDTO.setStatusCode(HttpStatus.OK.value());
			responseDTO.setMessage("All orders Fetched Successfully");
		} catch (Exception exception) {
			responseDTO.setError("Error fetching all orders: " + exception.getMessage());
			responseDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return responseDTO;
	}
	
	private OrderDTO populateOrderDTOFromFoodOrder(FoodOrder foodOrder) {
		OrderDTO orderDTO = new OrderDTO(foodOrder.getOrderId(), foodOrder.getDate());
		orderDTO.setOrderDetails(foodOrder.getOrderDetails());
		orderDTO.setTotalPrice(foodOrder.getPrice());
		orderDTO.setUserId(foodOrder.getUser().getId());
		orderDTO.setUserName(foodOrder.getUser().getName());
		
		return orderDTO;
	}

	@Override
	public GenericResponseDTO<List<OrderDTO>> getUserOrder(long userId) {
		GenericResponseDTO<List<OrderDTO>> responseDTO = new GenericResponseDTO<List<OrderDTO>>();
		List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();
		try {
			User user = userRepository.getReferenceById(userId);
			
			user.getFoodOrders().forEach(order -> {
				OrderDTO orderDTO = populateOrderDTOFromFoodOrder(order);
				ordersDTO.add(orderDTO);
			});

			responseDTO.setData(ordersDTO);
			
			responseDTO.setStatusCode(HttpStatus.OK.value());
			responseDTO.setMessage("Orders Fetched Successfully");
		} catch (Exception exception) {
			responseDTO.setError("Error fetching orders: " + exception.getMessage());
			responseDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		
		return responseDTO;
	}
}
