package com.dietservice.dao.nutrition.impl;

import com.dietservice.dao.nutrition.NutritionDAO;
import com.dietservice.domain.Nutrition;
import com.dietservice.repository.NutritionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.List;

public class NutritionDAOImpl implements NutritionDAO {

    NutritionRepository nutritionRepository;

    @Autowired
    public NutritionDAOImpl(NutritionRepository nutritionRepository){
        this.nutritionRepository = nutritionRepository;
    }

    @Override
    public Nutrition get(Long id) {
        return nutritionRepository.findOne(id);
    }

    @Override
    public List<Nutrition> getAllByDate(Date date) {
        return nutritionRepository.findByDate(date);
    }

    @Override
    public Nutrition save(Nutrition nutrition) {
        return nutritionRepository.save(nutrition);
    }
}
