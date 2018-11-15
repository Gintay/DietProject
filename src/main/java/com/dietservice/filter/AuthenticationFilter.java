package com.dietservice.filter;

import com.dietservice.utils.DietServiceConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class AuthenticationFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    private static final String BASIC_AUTHORIZATION = "BASIC ";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {


        boolean authorizationSucceed = false;

        try {
            if (request instanceof HttpServletRequest) {
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                String authorization = httpServletRequest.getHeader("Authorization");

                if (authorization.toUpperCase().startsWith(BASIC_AUTHORIZATION)) {
                    String userpassEncoded = authorization.substring(BASIC_AUTHORIZATION.length());
                    byte[] decodedBytes = Base64.getDecoder().decode(userpassEncoded);
                    String userpassDecoded = new String(decodedBytes, StandardCharsets.UTF_8);
                    String[] credentials = userpassDecoded.split(":");
                    String username = credentials[0];
                    String password = credentials[1];

                    if (username.equals(DietServiceConstants.AUTHENTICATION_USERNAME)
                            && password.equals(DietServiceConstants.AUTHENTICATION_PASSWORD)) {
                        authorizationSucceed = true;
                        chain.doFilter(request, response);
                    }
                }
            }
        } catch (Exception exc){
            logger.error("Authorization failed. {}", exc.getMessage());
        }

        if (!authorizationSucceed){
            PrintWriter out = response.getWriter();
            out.print("Username or password error!");
            out.close();
        }
    }

    @Override
    public void destroy() {
    }
}
