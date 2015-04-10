package com.example.heshitha.story.common;

import android.graphics.Bitmap;
import android.graphics.Typeface;

import com.example.heshitha.story.beanclasses.Story_Bean;
import com.example.heshitha.story.beanclasses.Story_Category_Bean;
import com.example.heshitha.story.beanclasses.User_Bean;

import java.util.List;

/**
 * Created by Heshitha on 3/16/2015.
 */
public class CommonDataHolder {
    public static User_Bean LoggedUser;

    public static List<Story_Category_Bean> lstStoryCategories;

    public static List<Story_Bean> lstStories;

    public static String PREFS_NAME = "com.example.heshitha.story.LOGINDETAILS";
    public static final int SELECT_PICTURE = 1;

    public static Story_Bean getStory(int storyID){

        Story_Bean story = new Story_Bean();

        if(lstStories != null) {
            for (int i = 0; i < lstStories.size(); i++) {
                if (lstStories.get(i).getStoryID() == storyID) {
                    story = lstStories.get(i);
                    break;
                }
            }
        }

        return story;
    }

    public static Typeface RaleWay;
    public static Typeface LatoLight;
    public static Typeface raavi;
    public static Typeface timesNewRomen;
    public static Typeface timesNewRomenBold;
    public static Typeface helviticaNeue;
    public static Typeface centurygothic;
    public static Typeface LatoBold;
    public static Typeface helviticaNeueLight;
    public static Typeface RaleWayLight;
    public static Typeface RaleWayMedium;
    public static Typeface HelveticaNeueLTSTDMD;
    public static Typeface LatoRegular;
    public static Typeface LucidaBright;
    public static Typeface HelveticaNeueBold;
    public static Typeface ChoplinMediumDemo;

    public static String WebURLAddress = "http://www.ezcim.com/story/";

    public static String AnonymousAccountWriteStory = "AnonymousAccountWriteStory";
    public static String GoToCategory = "GoToCategory";
    public static String HomePageMenuItem = "HomePageMenuItem";
    public static String StoryMyProfile = "StoryMyProfile";

    public static Bitmap SampleDefaultImage;
    public static Bitmap DefaultUserImage;
    public static Bitmap DefaultStoryImage;

    public static Story_Bean selectedStoryBean = new Story_Bean();
    public static Story_Bean writtenStoryBean = new Story_Bean();

}
