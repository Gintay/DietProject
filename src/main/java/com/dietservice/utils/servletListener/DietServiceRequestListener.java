package com.dietservice.utils.servletListener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class DietServiceRequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent requestEvent) {
        System.out.println("Received request from: " + requestEvent.getServletRequest().getRemoteAddr());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent requestEvent) {
    }
}
