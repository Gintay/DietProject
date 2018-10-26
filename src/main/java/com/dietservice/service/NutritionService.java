package com.dietservice.service;

import com.dietservice.domain.Dish;
import com.dietservice.domain.Nutrition;
import com.dietservice.dto.DishDto;
import com.dietservice.dto.NutritionDto;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.sql.Date;

public interface NutritionService {
    NutritionDto getNutrition(Long id);
    NutritionDto saveNutrition(NutritionDto nutritionDto);
    DishDto getNutritionDish(Long id);
    JSONObject getSummaryCalloriesJSON(Date date);
    BufferedImage getSummaryCaloriesImage(Date date);
    DishDto getDish(Long id);
    DishDto saveDish(DishDto dish);
}
