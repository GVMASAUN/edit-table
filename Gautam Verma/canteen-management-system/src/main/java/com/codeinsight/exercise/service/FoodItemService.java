package com.codeinsight.exercise.service;

import java.util.List;

import com.codeinsight.exercise.DTO.FoodItemDTO;
import com.codeinsight.exercise.DTO.ResponseDTO;

public interface FoodItemService {
	void createFoodItem(FoodItemDTO foodItemDTO);

	void updateItem(FoodItemDTO foodItemDTO);

	ResponseDTO getAllFoodItems();
}
