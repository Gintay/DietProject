package com.dietservice.filter;

import com.dietservice.utils.DietServiceProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        String base64Password = request.getParameter("password");
        if (base64Password != null) {
            byte[] decodedBytes = Base64.getDecoder().decode(base64Password); // YWRtaW4=
            String decodedPassword = new String(decodedBytes, "UTF-8");

            if (decodedPassword.equals(DietServiceProperties.AUTHENTICATION_PASSWORD)){
                chain.doFilter(request, response);
            } else {
                PrintWriter out = response.getWriter();
                out.print("username or password error!");
                out.close();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
