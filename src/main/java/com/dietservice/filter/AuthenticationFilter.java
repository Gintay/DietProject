package com.dietservice.filter;

import com.dietservice.utils.DietServiceProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

@Component
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        boolean passError = true;

        String base64Password = request.getParameter("password");
        if (base64Password != null) {
            byte[] decodedBytes = Base64.getDecoder().decode(base64Password); // YWRtaW4=
            String decodedPassword = new String(decodedBytes, "UTF-8");

            if (decodedPassword.equals(DietServiceProperties.AUTHENTICATION_PASSWORD)){
                passError = false;
                chain.doFilter(request, response);
            }
        }

        if (passError){
            PrintWriter out = response.getWriter();
            out.print("Username or password error!");
            out.close();
        }
    }

    @Override
    public void destroy() {

    }
}
