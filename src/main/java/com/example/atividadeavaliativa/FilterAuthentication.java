package com.example.atividadeavaliativa;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

@WebFilter(filterName = "FilterAuthentication", urlPatterns = "/*")
public class FilterAuthentication implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    public Boolean checkSessionExist(String sessionId, HttpServletRequest httpReq){

        Enumeration<String> attributes = httpReq.getSession().getAttributeNames();

        boolean response = false;

        while (attributes.hasMoreElements()) {
            String attribute = (String) attributes.nextElement();
            System.out.println(sessionId);
            if (httpReq.getSession().getAttribute(attribute).equals(sessionId)) {
                response = true;
            }
            System.out.println(attribute+" : "+httpReq.getSession().getAttribute(attribute));
        }
        return response;
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
                            System.out.println(checkSessionExist(cookie.getValue(), httpReq));
                            if (checkSessionExist(cookie.getValue(), httpReq)){
                                httpReq.getRequestDispatcher("index.jsp").forward(request, response);
                            }
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
