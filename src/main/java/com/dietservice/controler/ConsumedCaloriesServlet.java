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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet(urlPatterns = "/consumedCalories/*")
public class ConsumedCaloriesServlet extends HttpServlet {

    private NutritionService nutritionService;

    @Autowired
    public ConsumedCaloriesServlet(NutritionService nutritionService){
        this.nutritionService = nutritionService;
    }

    @Override
    protected void doGet (HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String pathParameters = request.getPathInfo();

        Date date = null;
        try {
            date = format.parse(pathParameters);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PrintWriter writer = response.getWriter();
        writer.println(nutritionService.getSummaryCallories(new java.sql.Date(date.getTime())));
    }
}

