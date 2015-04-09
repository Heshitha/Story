package com.example.heshitha.story.beanclasses;

import android.graphics.Bitmap;

/**
 * Created by Heshitha on 3/16/2015.
 */
public class Story_Category_Bean {
    private int id;
    private String name;
    private int count;
    private String imageName;
    private Bitmap Image;
    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Story_Category_Bean(int id, String name, int count) {

        this.id = id;
        this.name = name;
        this.count = count;
    }

    public Story_Category_Bean(){
        this.id = 0;
        this.name = "";
        this.count = 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
