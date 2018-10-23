package com.dietservice.controler;

import com.dietservice.domain.Dish;
import com.dietservice.domain.Nutrition;
import com.dietservice.service.NutritionService;
import com.dietservice.utils.listener.RequestEvent;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/nutrition")
public class DishController {

    private NutritionService nutritionService;
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public DishController(NutritionService nutritionService, ApplicationEventPublisher applicationEventPublisher){
        this.nutritionService = nutritionService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @GetMapping(value = "/{id}")
    public Nutrition getNutrition(@PathVariable("id") Long id, HttpServletRequest request){
        RequestEvent requestEvent = new RequestEvent(this, request);
        applicationEventPublisher.publishEvent(requestEvent);
        return nutritionService.getNutrition(id);
    }

//    @GetMapping(value = "/summarycalories/{date}")
//    public String getSummaryCalories(@PathVariable("date") Date date){
//        return nutritionService.getSummaryCallories(date).toString();
//    }

    @GetMapping(value = "/dish/{id}")
    public Dish getDish(@PathVariable("id") Long id){
        return nutritionService.getDish(id);
    }

    @PostMapping(value = "/dish", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dish saveDish(@RequestBody Dish dish){
        return nutritionService.saveDish(dish);
    }
}
