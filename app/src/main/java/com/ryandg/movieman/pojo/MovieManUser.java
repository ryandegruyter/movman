package com.ryandg.movieman.pojo;

/**
 * Created by Ryan De Gruyter on 26/05/2015.
 */
public class MovieManUser {
    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public MovieManUser() {
    }

    public MovieManUser(String userName, int id) {
        this.userName = userName;
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
