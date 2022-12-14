package com.example.atividadeavaliativa;

import com.example.atividadeavaliativa.Controller.Users;
import com.example.atividadeavaliativa.entity.User;

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
        String keepLogged = request.getParameter("keepLogged");

        if((username.equals("leo") || username.equals("prof")) && password.equals("123")) {

            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            User newUser = new User(username, session);

            if (!Users.checkIfUserExist(username)) {
                Users.addUser(newUser);
            } else {
                Users.updateSession(newUser);
            }

            if(keepLogged != null) {
                Cookie ck = new Cookie("keepLogged", username);
                ck.setMaxAge(60*60*24*7);
                response.addCookie(ck);
            }

            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }else{
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }

    }

    public void destroy() {
    }
}