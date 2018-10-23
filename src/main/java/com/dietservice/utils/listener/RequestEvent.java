package com.dietservice.utils.listener;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletRequest;

public class RequestEvent extends ApplicationEvent {

    private HttpServletRequest request;

    public RequestEvent(Object source, HttpServletRequest request) {
        super(source);
        this.request = request;
    }

    public String getRequestInfo() {
        String requestPathInfo = request.getPathInfo() != null ? request.getPathInfo() : "";
        String requestedSorce = request.getContextPath() + request.getServletPath() + requestPathInfo;
        String requestInfo = String.format("Received request from host - %s port - %d, request - %s", request.getRemoteHost(),
                request.getRemotePort(), requestedSorce);

        return requestInfo;
    }
}
