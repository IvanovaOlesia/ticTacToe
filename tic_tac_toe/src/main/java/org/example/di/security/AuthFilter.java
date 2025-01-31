package org.example.di.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Base64;

@Service
public class AuthFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String authHeader =  request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        String base64Credentials = authHeader.substring("Basic ".length());
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));

        // Разделяем "логин:пароль"
        String[] values = credentials.split(":", 2);
        if (values.length != 2) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Authorization header format");
            return;
        }
     filterChain.doFilter(servletRequest, servletResponse);

    }
}
