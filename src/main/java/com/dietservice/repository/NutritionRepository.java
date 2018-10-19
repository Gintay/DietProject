package com.dietservice.repository;

import com.dietservice.domain.Nutrition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface NutritionRepository extends CrudRepository<Nutrition, Long> {
    List<Nutrition> findByDate(Date date);
}
