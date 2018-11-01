package com.dietservice.dto;

public class SummaryCaloriesDto {
    private long calories;

    public SummaryCaloriesDto() {
    }

    public SummaryCaloriesDto(long calories) {
        this.calories = calories;
    }

    public long getCalories() {
        return calories;
    }

    public void setCalories(long calories) {
        this.calories = calories;
    }
}
