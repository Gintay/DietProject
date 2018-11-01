package com.dietservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.HashMap;

@SpringBootApplication
public class Launcher {
    public static void main(String[] args) {
        SpringApplicationBuilder application = new SpringApplicationBuilder(Launcher.class);
        application.run(args);
    }
}
