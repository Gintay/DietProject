package com.dietservice.controler;

import com.dietservice.service.NutritionService;
import com.dietservice.utils.listener.RequestEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
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
                          HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = "";

        try {
            RequestEvent requestEvent = new RequestEvent(this, request);
            applicationEventPublisher.publishEvent(requestEvent);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            pathInfo = request.getPathInfo();

            String pathParameters = pathInfo.replaceAll("/", "");
            Date utilDate = format.parse(pathParameters);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            String contentType = request.getContentType();
            if (contentType != null && contentType.equalsIgnoreCase("image/png")){
                BufferedImage bufferedImage = nutritionService.getSummaryCaloriesImage(sqlDate);
                OutputStream out = response.getOutputStream();
                ImageIO.write(bufferedImage, "png", out);
                out.close();
            } else {
                writeResponse(response, nutritionService.getSummaryCalloriesJSON(sqlDate));
            }
        } catch (ParseException exc) {
            writeResponse(response, String.format("Failed to parse date %s. %s", pathInfo,  exc.getMessage()));
        } catch (NullPointerException exc) {
            String errorMessage = "Failed to get summary results. " + exc.getMessage();
            writeResponse(response, errorMessage);
        }
    }

    private void writeResponse(HttpServletResponse response, Object responseMessage) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println(responseMessage);
        writer.close();
    }
}

