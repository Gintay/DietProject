package com.dietservice.service.impl;

import com.dietservice.dao.dish.DishDAO;
import com.dietservice.dao.nutrition.NutritionDAO;
import com.dietservice.domain.Dish;
import com.dietservice.domain.Nutrition;
import com.dietservice.dto.SummaryCaloriesDto;
import com.dietservice.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.util.List;

@Service
public class NutritionServiceImpl implements NutritionService {

    private DishDAO dishDAO;
    private NutritionDAO nutritionDAO;

    @Autowired
    public NutritionServiceImpl(NutritionDAO nutritionDAO, DishDAO dishDAO){
        this.nutritionDAO = nutritionDAO;
        this.dishDAO = dishDAO;
    }

    @Override
    public Nutrition getNutrition(Long id) {
        return nutritionDAO.get(id);
    }

    @Override
    public Nutrition saveNutrition(Nutrition nutrition) {
        Nutrition newNutrition = null;

        // if only dish id is set, to nutrition is added existing dish
        Dish dish = nutrition.getDish();
        if (dish.getId() != 0 && dish.getName() == null && dish.getCalories() == 0){
            newNutrition = new Nutrition();
            newNutrition.setId(nutrition.getId());
            newNutrition.setDate(nutrition.getDate());

            Dish savedDish = dishDAO.get(dish.getId());
            newNutrition.setDish(savedDish);

            newNutrition.setWeight(nutrition.getWeight());

        } else {
            newNutrition = nutrition;
        }

        return nutritionDAO.save(newNutrition);
    }

    @Override
    public Dish getNutritionDish(Long id) {
        return nutritionDAO.get(id).getDish();
    }

    @Override
    public SummaryCaloriesDto getSummaryCaloriesJSON(Date date) {
        long summaryCalories = getSummaryCallories(date);
        SummaryCaloriesDto summaryCaloriesDto = new SummaryCaloriesDto(summaryCalories);

        return summaryCaloriesDto;
    }

    @Override
    public BufferedImage getSummaryCaloriesImage(Date date){
        long summaryResult = getSummaryCallories(date);

        BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();

        graphics.drawString(String.valueOf(summaryResult), 20, 20);

        return bufferedImage;
    }

    private long getSummaryCallories(Date date){
        List<Nutrition> nutritions = nutritionDAO.getAllByDate(date);
        long summaryResult = 0;
        for(Nutrition nutrition : nutritions){
            long dishWeight = nutrition.getWeight();
            Dish dish = nutrition.getDish();
            long calories = dish.getCalories();
            if (calories > 0){
                summaryResult += dishWeight / 100 * calories;
            }
        }

        return summaryResult;
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
