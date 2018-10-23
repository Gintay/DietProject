package com.dietservice.controler;

import com.dietservice.service.NutritionService;
import com.dietservice.utils.listener.RequestEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

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

public class ConsumedCaloriesServlet extends HttpServlet {

    private NutritionService nutritionService;
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public ConsumedCaloriesServlet(NutritionService nutritionService, ApplicationEventPublisher applicationEventPublisher){
        this.nutritionService = nutritionService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    protected void doGet (HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        RequestEvent requestEvent = new RequestEvent(this, request);
        applicationEventPublisher.publishEvent(requestEvent);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String pathInfo = request.getPathInfo();

        String pathParameters = pathInfo.replaceAll("/", "");
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

