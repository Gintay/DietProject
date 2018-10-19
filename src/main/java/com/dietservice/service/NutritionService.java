package com.dietservice.service;

import com.dietservice.domain.Dish;
import com.dietservice.domain.Nutrition;
import org.json.JSONObject;

import java.sql.Date;

public interface NutritionService {
    Nutrition getNutrition(Long id);
    Nutrition saveNutrition(Nutrition nutrition);
    JSONObject getSummaryCallories(Date date);
    Dish getDish(Long id);
    Dish saveDish(Dish dish);
}
