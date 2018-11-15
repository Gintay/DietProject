package com.dietservice.controler;

import com.dietservice.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;

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

    private final NutritionService nutritionService;

    @Autowired
    public ConsumedCaloriesServlet(NutritionService nutritionService){
        this.nutritionService = nutritionService;
    }

    @Override
    protected void doGet (HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        String pathInfo = "";

        try {
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
                writeResponse(response, nutritionService.getSummaryCaloriesJSON(sqlDate));
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

