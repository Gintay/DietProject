package com.dietservice.dto;

import com.dietservice.domain.Dish;

public class DishDto {

    private long id;
    private String name;
    private long calories;

    public DishDto(){
    }

    public DishDto(Dish dish){
        this.id = dish.getId();
        this.name = dish.getName();
        this.calories = dish.getCalories();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCalories() {
        return calories;
    }

    public void setCalories(long calories) {
        this.calories = calories;
    }
}
