package com.codeinsight.exercise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.codeinsight.exercise.DTO.FoodItemDTO;
import com.codeinsight.exercise.DTO.ResponseDTO;
import com.codeinsight.exercise.entity.FoodItem;
import com.codeinsight.exercise.repository.FoodItemRepository;

@Service
public class FoodItemServiceImpl implements FoodItemService{

	@Autowired
	FoodItemRepository foodItemRepository;
	
	@Override
	public void createFoodItem(FoodItemDTO foodItemDTO) {
		FoodItem foodItem = new FoodItem();
		foodItem.setItemName(foodItemDTO.getItemName());
		foodItem.setItemPrice(foodItemDTO.getItemPrice());
		this.saveFoodItem(foodItem);
	}
	
	private void saveFoodItem(FoodItem foodItem) {
		foodItemRepository.save(foodItem);
	}

	@Override
	public void updateItem(FoodItemDTO foodItemDTO) {
		FoodItem foodItem = foodItemRepository.getReferenceById(foodItemDTO.getItemId());
		foodItem.setItemName(foodItemDTO.getItemName());
		foodItem.setItemPrice(foodItemDTO.getItemPrice());
		
		foodItemRepository.save(foodItem);
	}

	@Override
	public ResponseDTO getAllFoodItems() {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			List<FoodItem> foodItems = foodItemRepository.findAll();
			responseDTO.setFoodItems(foodItems);
			responseDTO.setMessage("Food Items fetched Successfully");
			responseDTO.setStatusCode(200);
		} catch(Exception exception) {
			responseDTO.setStatusCode(500);
			responseDTO.setError(exception.getMessage());
		}
		return responseDTO;
	}
}
