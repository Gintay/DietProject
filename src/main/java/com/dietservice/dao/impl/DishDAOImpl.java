package com.dietservice.dao.impl;

import com.dietservice.dao.DishDAO;
import com.dietservice.domain.Dish;
import com.dietservice.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DishDAOImpl implements DishDAO {

    private DishRepository dishRepository;

    @Autowired
    public DishDAOImpl(DishRepository dishRepository){
        this.dishRepository = dishRepository;
    }

    @Override
    public Dish get(Long id) {
        return dishRepository.findOne(id);
    }

    @Override
    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }
}
