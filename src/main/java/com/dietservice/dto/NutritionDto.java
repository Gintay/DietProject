package com.dietservice.dto;

import com.dietservice.domain.Nutrition;

import java.sql.Date;

public class NutritionDto {

    private long id;
    private Date date;
    private DishDto dishDto;
    private long weight;

    public NutritionDto(){
    }

    public NutritionDto(Nutrition nutrition){
        this.id = nutrition.getId();
        this.date = nutrition.getDate();
        this.dishDto = new DishDto(nutrition.getDish());
        this.weight = nutrition.getWeight();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DishDto getDishDto() {
        return dishDto;
    }

    public void setDishDto(DishDto dishDto) {
        this.dishDto = dishDto;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }
}
