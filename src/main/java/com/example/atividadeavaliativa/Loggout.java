package com.example.atividadeavaliativa;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "loggout", value = "/loggout")
public class Loggout extends HttpServlet {
    private ArrayList<String> users;

    public void init() {
        users = new ArrayList<String>();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        session.setAttribute("user", null);

        Cookie ck = new Cookie("keepLogged", "");
        ck.setMaxAge(0);
        response.addCookie(ck);

        response.sendRedirect(request.getContextPath() + "/login.jsp");

    }

    public void destroy() {
    }
}