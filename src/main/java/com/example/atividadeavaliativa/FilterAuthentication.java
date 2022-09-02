package com.example.atividadeavaliativa;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "FilterAuthentication")
public class FilterAuthentication implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String targetURI= httpReq.getRequestURI();
        boolean isLogged = (boolean)httpReq.getSession().getAttribute("userId");

        if (isLogged) {
            chain.doFilter(request, response);
        }else{
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
