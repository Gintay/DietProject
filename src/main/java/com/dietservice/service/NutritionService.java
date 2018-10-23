package com.dietservice.service;

import com.dietservice.domain.Dish;
import com.dietservice.domain.Nutrition;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.sql.Date;

public interface NutritionService {
    Nutrition getNutrition(Long id);
    Nutrition saveNutrition(Nutrition nutrition);
    Dish getNutritionDish(Long id);
    JSONObject getSummaryCalloriesJSON(Date date);
    public BufferedImage getSummaryCaloriesImage(Date date);
    Dish getDish(Long id);
    Dish saveDish(Dish dish);
}
