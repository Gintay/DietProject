package com.dietservice.config;

import com.dietservice.controler.ConsumedCaloriesServlet;
import com.dietservice.dao.dish.impl.DishDAOImpl;
import com.dietservice.dao.dish.DishDAO;
import com.dietservice.dao.nutrition.NutritionDAO;
import com.dietservice.dao.nutrition.impl.NutritionDAOImpl;
import com.dietservice.filter.AuthenticationFilter;
import com.dietservice.repository.DishRepository;
import com.dietservice.repository.NutritionRepository;
import com.dietservice.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.dietservice.controler" })
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
        registrationBean.addUrlPatterns("/consumedCalories/*");

        return registrationBean;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean(@Autowired NutritionService nutritionService){
        return new ServletRegistrationBean(new ConsumedCaloriesServlet(nutritionService),"/consumedCalories/*");
    }
}
