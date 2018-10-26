package com.dietservice.domain;

import com.dietservice.dto.NutritionDto;
import com.dietservice.utils.DietServiceConstants;

import javax.persistence.*;

@Entity
@Table(name = DietServiceConstants.NUTRITION_TABLE_NAME)
public class Nutrition {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private java.sql.Date date;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Dish.class)
  @JoinColumn(name = "dishid", referencedColumnName = "id")
  private Dish dish;

  private long weight;

  public Nutrition(){
  }

  public Nutrition(NutritionDto nutritionDto){
      this.id = nutritionDto.getId();
      this.date = nutritionDto.getDate();
      this.dish = new Dish(nutritionDto.getDishDto());
      this.weight = nutritionDto.getWeight();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public java.sql.Date getDate() {
    return date;
  }

  public void setDate(java.sql.Date date) {
    this.date = date;
  }

  public Dish getDish() {
    return dish;
  }

  public void setDish(Dish dish) {
    this.dish = dish;
  }

  public long getWeight() {
    return weight;
  }

  public void setWeight(long weight) {
    this.weight = weight;
  }

}
