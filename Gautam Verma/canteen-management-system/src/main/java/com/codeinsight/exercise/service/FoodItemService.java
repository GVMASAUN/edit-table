package com.codeinsight.exercise.service;

import com.codeinsight.exercise.DTO.FoodItemDTO;

public interface FoodItemService {
	void createFoodItem(FoodItemDTO foodItemDTO);

	void updateItem(FoodItemDTO foodItemDTO);
}
