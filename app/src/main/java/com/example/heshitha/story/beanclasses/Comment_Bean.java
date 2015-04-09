package com.example.heshitha.story.beanclasses;

import java.util.Date;

/**
 * Created by Heshitha on 3/16/2015.
 */
public class Comment_Bean {
    private int id;
    private User_Bean user;
    private Story_Bean story;
    private String comment;
    private double rate;
    private Date createdDate;
    private Date modifiedDate;
    private int status;

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

    public Story_Bean getStory() {
        return story;
    }

    public void setStory(Story_Bean story) {
        this.story = story;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Comment_Bean() {
        this.id = 0;
        this.user = new User_Bean();
        this.story = new Story_Bean();
        this.comment = "";
        this.rate = 0;
        this.createdDate = new Date();
        this.modifiedDate = new Date();
        this.status = 0;
    }
}
