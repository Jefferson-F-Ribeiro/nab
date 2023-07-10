package com.example.nab;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User implements Serializable {
    private String username;
    private String name;
    private String email;
    private String password;
    private int level;
    private List<Integer> scoresStageOne;

    private boolean firstLogin;

    public User() {
        this.firstLogin = true;
        scoresStageOne = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            scoresStageOne.add(0);
        }
    }

    public User(String username, String name, String email, String password, int level) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.level = level;
        this.firstLogin = true;

        scoresStageOne = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            scoresStageOne.add(0);
        }
    }


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

    public void addScoreStageOne(int score) {
        scoresStageOne.add(score);
        Collections.sort(scoresStageOne, Collections.reverseOrder());
        if (scoresStageOne.size() > 5) {
            scoresStageOne.remove(scoresStageOne.size() - 1);
        }
    }
    public List<Integer> getScoresStageOne() {
        return scoresStageOne;
    }

    public void setScoresStageOne(List<Integer> i){ this.scoresStageOne = i; }

    public void setFirstLogin(boolean b){ this.firstLogin = b; }

    public boolean getFirstLogin(){ return this.firstLogin; }

}

