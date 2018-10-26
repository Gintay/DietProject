package com.dietservice.domain;

import com.dietservice.dto.DishDto;
import com.dietservice.utils.DietServiceConstants;

import javax.persistence.*;

@Entity
@Table(name = DietServiceConstants.DISH_TABLE_NAME)
public class Dish {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String name;
  private long calories;

  public Dish(){
  }

  public Dish(DishDto dishDto){
    this.id = dishDto.getId();
    this.name = dishDto.getName();
    this.calories = dishDto.getCalories();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getCalories() {
    return calories;
  }

  public void setCalories(long calories) {
    this.calories = calories;
  }
}
