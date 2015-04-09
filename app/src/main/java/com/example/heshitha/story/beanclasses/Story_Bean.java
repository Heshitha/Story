package com.example.heshitha.story.beanclasses;

import android.graphics.Bitmap;

import java.util.Date;
import java.util.List;

/**
 * Created by Heshitha on 3/16/2015.
 */
public class Story_Bean {

    private String fullStory;
    private List<Comment_Bean> comments;
    private int totalComments;
    private int storyID;
    private Story_Category_Bean category;
    private Author_Bean author;
    private String title;
    private String summery;
    private String imageName;
    private String language;
    private double rate;
    private Date createdDate;
    private Date modifiedDate;
    private int status;
    private Bitmap image;

    public Story_Bean() {
    }

    public String getFullStory() {
        return fullStory;
    }

    public void setFullStory(String fullStory) {
        this.fullStory = fullStory;
    }

    public List<Comment_Bean> getComments() {
        return comments;
    }

    public void setComments(List<Comment_Bean> comments) {
        this.comments = comments;
    }

    public int getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(int totalComments) {
        this.totalComments = totalComments;
    }

    public int getStoryID() {
        return storyID;
    }

    public void setStoryID(int storyID) {
        this.storyID = storyID;
    }

    public Story_Category_Bean getCategory() {
        return category;
    }

    public void setCategory(Story_Category_Bean category) {
        this.category = category;
    }

    public Author_Bean getAuthor() {
        return author;
    }

    public void setAuthor(Author_Bean author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummery() {
        return summery;
    }

    public void setSummery(String summery) {
        this.summery = summery;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
