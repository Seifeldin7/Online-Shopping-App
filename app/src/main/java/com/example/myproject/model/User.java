package com.example.myproject.model;

public class User {
    private String Name;
    private int image;
    private String mail;
    private int userID;
    private int numOfItems;

    public User() {
    }

    public User(String name, int image, String mail, int userID) {
        Name = name;
        this.image = image;
        this.mail = mail;
        this.userID = userID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }
}
