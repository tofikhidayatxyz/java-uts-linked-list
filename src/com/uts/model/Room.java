package com.uts.model;

import java.util.ArrayList;

public class Room {
    public ArrayList<User> users = new ArrayList<>();
    public String name = null;
    public String type = null;
    public Integer maxUser = null;

    public Room(String name, String type, Integer maxUser) {
        this.name = name;
        this.type = type;
        this.maxUser = maxUser;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeAllUser() {
        this.users = new ArrayList<>();
    }
}
