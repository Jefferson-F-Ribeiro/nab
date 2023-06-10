package com.example.nab;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String name;
    private String email;
    private String password;
    private int level;

    public User() {
        // Default constructor
    }

    public User(String username, String name, String email, String password, int level) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.level = level;
    }

    // Getter and setter methods here...

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}

