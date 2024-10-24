package com.codeinsight.exercise.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeinsight.exercise.DTO.FoodItemDTO;
import com.codeinsight.exercise.entity.FoodItem;
import com.codeinsight.exercise.repository.FoodItemRepository;

import jakarta.persistence.EntityNotFoundException;
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
}
