package com.dietservice.service.impl;

import com.dietservice.dao.DishDAO;
import com.dietservice.domain.Dish;
import com.dietservice.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl implements DishService {

    private DishDAO dishDAO;

    @Autowired
    public DishServiceImpl(DishDAO dishDAO){
        this.dishDAO = dishDAO;
    }

    @Override
    public Dish getDish(Long id) {
        return dishDAO.get(id);
    }

    @Override
    public Dish saveDish(Dish dish) {
        return dishDAO.save(dish);
    }
}
