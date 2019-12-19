package com.example.myproject.model;

import java.util.Date;

public class Item {
    private int itemID;
    private String itemName;
    private int itemImage;
    private Date date;
    private double price;
    private int viewedNum;



    public Item(int itemID, String itemName, int itemImage, double price) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemImage = itemImage;
        this.price = price;
        this.date=new Date();
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public int getViewedNum() {
        return viewedNum;
    }

    public void setViewedNum(int viewedNum) {
        this.viewedNum = viewedNum;
    }
}
