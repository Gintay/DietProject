package com.dietservice.controler;

import com.dietservice.domain.Dish;
import com.dietservice.domain.Nutrition;
import com.dietservice.dto.DishDto;
import com.dietservice.dto.NutritionDto;
import com.dietservice.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class DietController {

    private final NutritionService nutritionService;

    @Autowired
    public DietController(NutritionService nutritionService){
        this.nutritionService = nutritionService;
    }

    @GetMapping(value = "/nutrition/{id}")
    public NutritionDto getNutrition(@PathVariable("id") Long id){
        NutritionDto nutritionDto = new NutritionDto(nutritionService.getNutrition(id));
        return nutritionDto;
    }

    @GetMapping(value = "/nutrition/{id}/dish")
    public DishDto getNutritionDish (@PathVariable("id") Long id) {
        DishDto dishDto = new DishDto(nutritionService.getNutritionDish(id));
        return dishDto;
    }

    @PostMapping(value = "/nutrition", consumes = MediaType.APPLICATION_JSON_VALUE)
    public NutritionDto saveNutrition(@RequestBody NutritionDto nutritionDto){
        Nutrition nutrition = new Nutrition(nutritionDto);
        Nutrition savedNutrition =  nutritionService.saveNutrition(nutrition);
        NutritionDto savedNutritionDto = new NutritionDto(savedNutrition);
        return savedNutritionDto;
    }

    @GetMapping(value = "/dish/{id}")
    public DishDto getDish(@PathVariable("id") Long id){
        DishDto dishDto = new DishDto(nutritionService.getDish(id));
        return dishDto;
    }

    @PostMapping(value = "/dish", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DishDto saveDish(@RequestBody DishDto dishDto){
        Dish dish = new Dish(dishDto);
        Dish savedDish = nutritionService.saveDish(dish);
        DishDto savedDishDto = new DishDto(savedDish);
        return savedDishDto;
    }

}
