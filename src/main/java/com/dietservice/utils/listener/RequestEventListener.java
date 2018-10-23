package com.dietservice.utils.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RequestEventListener implements ApplicationListener<RequestEvent> {

    @Override
    public void onApplicationEvent(RequestEvent event) {
        System.out.println("Received request - " + event.getRequestInfo());
    }
}
