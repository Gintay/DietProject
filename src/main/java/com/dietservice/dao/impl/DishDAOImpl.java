package com.dietservice.dao.impl;

import com.dietservice.dao.DishDAO;
import com.dietservice.domain.Dish;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DishDAOImpl implements DishDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public DishDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Dish get(Long id) {
        return null;
    }

    @Override
    public void save(Dish dish) {

    }
}
