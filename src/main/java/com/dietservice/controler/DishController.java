package com.dietservice.controler;

import com.dietservice.domain.Dish;
import com.dietservice.service.DishService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dishs")
public class DishController {

    private DishService dishService;

    public DishController(DishService dishService){
        this.dishService = dishService;
    }

    @GetMapping(value = "/{id}")
    public Dish getDish(@PathVariable("id") Long id){
        return dishService.getDish(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dish saveDish(@RequestBody Dish dish){
        return dishService.saveDish(dish);
    }
}
