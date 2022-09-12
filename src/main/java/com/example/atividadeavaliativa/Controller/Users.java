package com.example.atividadeavaliativa.Controller;

import com.example.atividadeavaliativa.entity.User;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Users {

    private static ArrayList<User> users = new ArrayList<>();

    private static int getIndex(String username){
        for (int c = 0;c < users.size(); c++) {
            if (users.get(c).getUsername().equals(username)) {
                return c;
            }
        }
        return -1;
    }

    public static ArrayList<User> getUsers(){
        return users;
    }

    public static void addUser(User user){
        users.add(user);
    }

    public static User getUserByUsername(String username) {
        User user = null;
        for (int c = 0; c < users.size(); c++) {
            if (users.get(c).getUsername().equals(username)) {
                user = users.get(c);
            }
        }
        return user;
    }

    public static void removeUser(String user){
        users.removeIf(userObj -> userObj.getUsername().equals(user));
    }

    public static boolean checkIfUserExist(String username){
        AtomicBoolean exist = new AtomicBoolean(false);
        users.forEach((user) -> {
            if (user.getUsername().equals(username)){
                exist.set(true);
            }
        });

        return exist.get();
    }

    public static void updateSession(User user){
        int index = getIndex(user.getUsername());
        if (index != -1) {
            users.get(index).setSession(user.getSession());
        }
    }

}
