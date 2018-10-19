package com.dietservice.controler;

import com.dietservice.domain.Dish;
import com.dietservice.domain.Nutrition;
import com.dietservice.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/nutrition")
public class DishController {

    private NutritionService nutritionService;

    @Autowired
    public DishController(NutritionService nutritionService){
        this.nutritionService = nutritionService;
    }

    @GetMapping(value = "/{id}")
    public Nutrition getNutrition(@PathVariable("id") Long id){
        return nutritionService.getNutrition(id);
    }

    @GetMapping(value = "/summarycalories/{date}")
    public long getSummaryCalories(@PathVariable("date") Date date){
        return nutritionService.getSummaryCallories(date);
    }

    @GetMapping(value = "/dish/{id}")
    public Dish getDish(@PathVariable("id") Long id){
        return nutritionService.getDish(id);
    }

    @PostMapping(value = "/dish", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dish saveDish(@RequestBody Dish dish){
        return nutritionService.saveDish(dish);
    }
}
