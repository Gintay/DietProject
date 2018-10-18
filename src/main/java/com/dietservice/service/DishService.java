package com.dietservice.service;

import com.dietservice.domain.Dish;

public interface DishService {
    Dish getDish(Long id);
    Dish saveDish(Dish dish);
}
