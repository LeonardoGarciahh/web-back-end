package com.example.atividadeavaliativa.entity;

import javax.servlet.http.HttpSession;

public class User {
    private String username;
    private HttpSession session;

    public User(String username, HttpSession session) {
        this.username = username;
        this.session = session;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
}
