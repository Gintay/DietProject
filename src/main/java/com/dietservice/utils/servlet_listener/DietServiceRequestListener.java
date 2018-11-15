package com.dietservice.utils.servlet_listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DietServiceRequestListener implements ServletRequestListener {

    private static final Logger logger = LoggerFactory.getLogger(DietServiceRequestListener.class);

    @Override
    public void requestInitialized(ServletRequestEvent requestEvent) {
        ServletRequest request = requestEvent.getServletRequest();
        String requestURI = "unknown";
        if (request instanceof HttpServletRequest){
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            requestURI = httpServletRequest.getRequestURI();
        }
        logger.info("Received request from host - {} port - {}. Requested resource - {}",
                request.getRemoteHost(), request.getRemotePort(), requestURI);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent requestEvent) {
    }
}
