package com.dietservice.controler;

import com.dietservice.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;


@WebServlet(urlPatterns = "/consumedCalories")
public class ConsumedCaloriesServlet extends HttpServlet {

    private NutritionService nutritionService;

    @Autowired
    public ConsumedCaloriesServlet(NutritionService nutritionService){
        this.nutritionService = nutritionService;
    }

    @Override
    protected void doGet (HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        Date date = new Date(Calendar.getInstance().getTimeInMillis()); //TODO get date
        PrintWriter writer = resp.getWriter();
        writer.println(nutritionService.getSummaryCallories(date));
    }
}

