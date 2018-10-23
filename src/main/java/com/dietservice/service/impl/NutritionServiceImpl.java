package com.dietservice.service.impl;

import com.dietservice.dao.dish.DishDAO;
import com.dietservice.dao.nutrition.NutritionDAO;
import com.dietservice.domain.Dish;
import com.dietservice.domain.Nutrition;
import com.dietservice.service.NutritionService;
import org.json.JSONObject;
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
        return nutritionDAO.save(nutrition);
    }

    @Override
    public Dish getNutritionDish(Long id) {
        return nutritionDAO.get(id).getDish();
    }

    @Override
    public JSONObject getSummaryCalloriesJSON(Date date) {
        long summaryResult = getSummaryCallories(date);

        JSONObject resultJSON = new JSONObject();
        resultJSON.append("calories", summaryResult);

        return resultJSON;
    }

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
            Dish dish = nutrition.getDish();
            long dishWeight = nutrition.getWeight();
            summaryResult += dishWeight / 100 * dish.getCalories();
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
