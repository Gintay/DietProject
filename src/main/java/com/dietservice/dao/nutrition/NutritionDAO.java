package com.dietservice.dao.nutrition;

import com.dietservice.domain.Nutrition;

import java.sql.Date;
import java.util.List;

public interface NutritionDAO {
    Nutrition get(Long id);
    List<Nutrition> getAllByDate(Date date);
    Nutrition save(Nutrition nutrition);

}
