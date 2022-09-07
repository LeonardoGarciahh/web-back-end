package com.example.atividadeavaliativa;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(filterName = "FilterAuthentication", urlPatterns = "/*")
public class FilterAuthentication implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }


    public void createForgetSession(String id, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("user", id);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("Processing filter...");
        try {
            HttpServletRequest httpReq = (HttpServletRequest) request;
            HttpServletResponse httpRes = (HttpServletResponse) response;
            String targetURI = httpReq.getRequestURI();

            Cookie[] cookies = httpReq.getCookies();


            if (cookies != null) {
                for(Cookie cookie : cookies) {
                    if(cookie.getName().equals("keepLogged")) {
                        boolean keepLogged = !cookie.getValue().equals("");
                        if(keepLogged && targetURI.contains("login.jsp")) {
                            createForgetSession(cookie.getValue(), httpReq);
                            httpReq.getRequestDispatcher("index.jsp").forward(request, response);
                        }
                    }
                }
            }

            if(httpReq.getSession().getAttribute("user") == null && targetURI.contains("index.jsp")) {
                httpRes.sendRedirect("login.jsp");
            }

            if(httpReq.getSession().getAttribute("user") != null && targetURI.contains("login.jsp")) {
                httpReq.getRequestDispatcher("index.jsp").forward(request, response);
            }

            chain.doFilter(request, response);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
