package com.example.atividadeavaliativa;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "authentication", value = "/authentication")
public class Authentication extends HttpServlet {
    private ArrayList<String> users;

    public void init() {
        users = new ArrayList<String>();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        session.setAttribute(username, 5);

        Object obj = session.getAttribute("userId");

        session.setMaxInactiveInterval(1800);

        Enumeration<String> values = request.getSession().getAttributeNames();


        Cookie[] cookieList = request.getCookies();
        String value=null;
        for(Cookie c:cookieList){
            System.out.println(c.getName());
            if (c.getName().equals("user")) {
                value = c.getValue();
                break;
            }
        }
//        while(values.hasMoreElements()){
//            response.getOutputStream().println(values.nextElement());
//        }

        Cookie ck = new Cookie("user", username);
        response.addCookie(ck);

        if (username.equals("leo") && password.equals("123")) {
            response.sendRedirect("/atividade_avaliativa_war_exploded/index.jsp");
        }

        System.out.println(session);

    }

    public void destroy() {
    }
}