package com.dietservice.dao;

import com.dietservice.domain.Dish;

public interface DishDAO {
    Dish get(Long id);
    Dish save(Dish dish);
}
