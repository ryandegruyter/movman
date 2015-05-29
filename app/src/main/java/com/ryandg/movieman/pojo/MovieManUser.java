package com.ryandg.movieman.pojo;

/**
 * Created by Ryan De Gruyter on 26/05/2015.
 */
public class MovieManUser {
    private String userName;
    private String passWord;

    public MovieManUser() {
    }

    public MovieManUser(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
