package com.example.atividadeavaliativa;

import com.example.atividadeavaliativa.Controller.Users;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "loggout", value = "/loggout")
public class Loggout extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();

        if (request.getSession().getAttribute("user") != null) {
            String username = request.getSession().getAttribute("user").toString();
            Users.removeUser(username);
        }

        session.setAttribute("user", null);

        Cookie ck = new Cookie("keepLogged", "");
        ck.setMaxAge(0);
        response.addCookie(ck);

        response.sendRedirect(request.getContextPath() + "/login.jsp");

    }

    public void destroy() {
    }
}