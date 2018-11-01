package com.dietservice.service;

import com.dietservice.domain.Dish;
import com.dietservice.domain.Nutrition;
import com.dietservice.dto.SummaryCaloriesDto;

import java.awt.image.BufferedImage;
import java.sql.Date;

public interface NutritionService {
    Nutrition getNutrition(Long id);
    Nutrition saveNutrition(Nutrition nutritionDto);
    Dish getNutritionDish(Long id);
    SummaryCaloriesDto getSummaryCaloriesJSON(Date date);
    BufferedImage getSummaryCaloriesImage(Date date);
    Dish getDish(Long id);
    Dish saveDish(Dish dish);
}
