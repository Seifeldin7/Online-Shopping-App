package com.example.myproject.model;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {
    private String itemID;
    private String itemName;
    private String itemImage;
    private String date;
    private double price;
    private int viewedNum;
    private String description;
    private int ownerID;
    private String category;

    public Item() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date current = new Date();
        this.date=formatter.format(current);
        this.viewedNum=0;
    }

//    public Item(int itemID, String itemName, int itemImage, double price) {
//        this.itemID = itemID;
//        this.itemName = itemName;
//        this.itemImage = itemImage;
//        this.price = price;
//        this.date=new Date();
//    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public void incItemView(int count) {
        this.viewedNum += count;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
