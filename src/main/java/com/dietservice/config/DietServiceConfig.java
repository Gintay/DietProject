package com.dietservice.config;

import com.dietservice.dao.dish.impl.DishDAOImpl;
import com.dietservice.dao.dish.DishDAO;
import com.dietservice.dao.nutrition.NutritionDAO;
import com.dietservice.dao.nutrition.impl.NutritionDAOImpl;
import com.dietservice.filter.AuthenticationFilter;
import com.dietservice.repository.DishRepository;
import com.dietservice.repository.NutritionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DietServiceConfig {

    @Bean
    public DishDAO getDishDAO(@Autowired DishRepository dishRepository){
        return new DishDAOImpl(dishRepository);
    }

    @Bean
    public NutritionDAO getNutritionDAO(@Autowired NutritionRepository nutritionRepository){
        return new NutritionDAOImpl(nutritionRepository);
    }

    @Bean
    public FilterRegistrationBean getAuthenticationFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        registrationBean.setFilter(new AuthenticationFilter());
        registrationBean.addUrlPatterns("/nutrition/summarycalories/*");

        return registrationBean;
    }
}
