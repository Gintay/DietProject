package com.dietservice.service.impl;

import com.dietservice.dao.dish.DishDAO;
import com.dietservice.dao.nutrition.NutritionDAO;
import com.dietservice.domain.Dish;
import com.dietservice.domain.Nutrition;
import com.dietservice.dto.DishDto;
import com.dietservice.dto.NutritionDto;
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
    public NutritionDto getNutrition(Long id) {
        NutritionDto nutritionDto = new NutritionDto(nutritionDAO.get(id));
        return nutritionDto;
    }

    @Override
    public NutritionDto saveNutrition(NutritionDto nutritionDto) {
        Nutrition nutrition = new Nutrition(nutritionDto);
        Nutrition resultNutrition = nutritionDAO.save(nutrition);
        NutritionDto resultNutritionDto1 = new NutritionDto(resultNutrition);
        return resultNutritionDto1;
    }

    @Override
    public DishDto getNutritionDish(Long id) {
        DishDto dishDto = new DishDto(nutritionDAO.get(id).getDish());
        return dishDto;
    }

    @Override
    public JSONObject getSummaryCalloriesJSON(Date date) {
        long summaryResult = getSummaryCallories(date);

        JSONObject resultJSON = new JSONObject();
        resultJSON.append("calories", summaryResult);

        return resultJSON;
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
    public DishDto getDish(Long id) {
        DishDto dishDto = new DishDto(dishDAO.get(id));
        return dishDto;
    }

    @Override
    public DishDto saveDish(DishDto dishDto) {
        Dish dish = new Dish(dishDto);
        Dish resultDish = dishDAO.save(dish);
        DishDto resultDishDto = new DishDto(resultDish);
        return resultDishDto;
    }
}
