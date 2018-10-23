package com.dietservice.controler;

import com.dietservice.domain.Dish;
import com.dietservice.domain.Nutrition;
import com.dietservice.service.NutritionService;
import com.dietservice.utils.listener.RequestEvent;
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
        publishRequestEvent(request);
        return nutritionService.getNutrition(id);
    }

    @PostMapping(value = "/nutrition", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Nutrition saveNutrition(@RequestBody Nutrition nutrition, HttpServletRequest request){
        publishRequestEvent(request);
        return nutritionService.saveNutrition(nutrition);
    }

    @GetMapping(value = "/dish/{id}")
    public Dish getDish(@PathVariable("id") Long id, HttpServletRequest request){
        publishRequestEvent(request);
        return nutritionService.getDish(id);
    }

    @PostMapping(value = "/dish", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dish saveDish(@RequestBody Dish dish, HttpServletRequest request){
        publishRequestEvent(request);
        return nutritionService.saveDish(dish);
    }

    private void publishRequestEvent(HttpServletRequest request){
        RequestEvent requestEvent = new RequestEvent(this, request);
        applicationEventPublisher.publishEvent(requestEvent);
    }
}
