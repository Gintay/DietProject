package com.dietservice.domain;

import com.dietservice.utils.DietServiceProperties;

import javax.persistence.*;

@Entity
@Table(name = DietServiceProperties.NUTRITION_TABLE_NAME)
public class Nutrition {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private java.sql.Date date;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private Dish dish;
  private long weight;

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
