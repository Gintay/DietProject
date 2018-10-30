package com.dietservice.controler;

import com.dietservice.dto.DishDto;
import com.dietservice.dto.NutritionDto;
import com.dietservice.service.NutritionService;
import com.dietservice.utils.listener.RequestEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DietController {

    private NutritionService nutritionService;
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public DietController(NutritionService nutritionService, ApplicationEventPublisher applicationEventPublisher){
        this.nutritionService = nutritionService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @GetMapping(value = "/nutrition/{id}")
    public NutritionDto getNutrition(@PathVariable("id") Long id, HttpServletRequest request){
        publishRequestEvent(request);
        return nutritionService.getNutrition(id);
    }

    @GetMapping(value = "/nutrition/{id}/dish")
    public DishDto getNutritionDish (@PathVariable("id") Long id, HttpServletRequest request) {
        publishRequestEvent(request);
        return nutritionService.getNutritionDish(id);
    }

    @PostMapping(value = "/nutrition", consumes = MediaType.APPLICATION_JSON_VALUE)
    public NutritionDto saveNutrition(@RequestBody NutritionDto nutritionDto, HttpServletRequest request){
        publishRequestEvent(request);
        return nutritionService.saveNutrition(nutritionDto);
    }

    @GetMapping(value = "/dish/{id}")
    public DishDto getDish(@PathVariable("id") Long id, HttpServletRequest request){
        publishRequestEvent(request);
        return nutritionService.getDish(id);
    }

    @PostMapping(value = "/dish", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DishDto saveDish(@RequestBody DishDto dishDto, HttpServletRequest request){
        publishRequestEvent(request);
        return nutritionService.saveDish(dishDto);
    }

    private void publishRequestEvent(HttpServletRequest request){
        RequestEvent requestEvent = new RequestEvent(this, request);
        applicationEventPublisher.publishEvent(requestEvent);
    }
}
