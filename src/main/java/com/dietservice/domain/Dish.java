package com.dietservice.domain;

import com.dietservice.utils.DietServiceProperties;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = DietServiceProperties.DISH_TABLE_NAME)
public class Dish {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String name;
  private long calories;


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
