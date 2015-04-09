package com.example.heshitha.story.beanclasses;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Heshitha on 3/16/2015.
 */
public class Author_Bean {
    private int id;
    private User_Bean user;
    private String name;
    private double rate;
    private Date createdDate;
    private Date modifiedDate;
    private int status;
    private Bitmap image;
    private String imageName;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Author_Bean() {
        this.id = 0;
        this.user = new User_Bean();
        this.name = "";
        this.rate = 0;
        this.createdDate = null;
        this.modifiedDate = null;
        this.status = 0;
        this.imageName = "";
    }

    public Author_Bean(int id, User_Bean user, String name, double rate, Date createdDate, Date modifiedDate, int status) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.rate = rate;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User_Bean getUser() {
        return user;
    }

    public void setUser(User_Bean user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
