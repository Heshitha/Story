package com.example.heshitha.story.asynctasks;

import android.app.ActivityManager;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.heshitha.story.R;
import com.example.heshitha.story.StoryListFragment;
import com.example.heshitha.story.StorySummeryActivity;
import com.example.heshitha.story.StorySummeryFragment;
import com.example.heshitha.story.adapters.StoryListAdapter;
import com.example.heshitha.story.beanclasses.Author_Bean;
import com.example.heshitha.story.beanclasses.Comment_Bean;
import com.example.heshitha.story.beanclasses.Story_Bean;
import com.example.heshitha.story.beanclasses.Story_Category_Bean;
import com.example.heshitha.story.beanclasses.User_Bean;
import com.example.heshitha.story.common.CommonDataHolder;
import com.example.heshitha.story.common.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Heshitha on 3/18/2015.
 */
public class LoadStoryList extends AsyncTask<Story_Bean, Void, Story_Bean> {

    Context context;
    GridView gridView;
    Fragment fragment;

    String jsonUrl = "http://www.ezcim.com/story/getStories?categoryid=";

    JSONArray dataJsonMainArr = null;

    List<Story_Bean> storyList = new ArrayList<Story_Bean>();

    ProgressDialog pDialog;

    RelativeLayout rlEmptyCategory;

    int categoryID = 0;
    public LoadStoryList(int categoryID, Context context, GridView gridView, Fragment fragment, RelativeLayout rlEmptyCategory){
        this.categoryID = categoryID;
        jsonUrl = "http://www.ezcim.com/story/getStories?categoryid="+categoryID;
        this.context = context;
        this.gridView = gridView;
        this.fragment = fragment;
        this.rlEmptyCategory = rlEmptyCategory;
    }

    @Override
    protected Story_Bean doInBackground(Story_Bean... params) {
        try{

            JsonParser jParser = new JsonParser();

            JSONObject json = jParser.getJSONFromUrl(jsonUrl);

            dataJsonMainArr = json.getJSONArray("Content");

            storyList = new ArrayList<Story_Bean>();

            for(int i = 0; i < dataJsonMainArr.length(); i++){
                try {
                    JSONObject storyObject = dataJsonMainArr.getJSONObject(i);

                    JSONObject categoryObject = storyObject.getJSONObject("Category");
                    Story_Category_Bean storyCategoryBean = new Story_Category_Bean();
                    storyCategoryBean.setId(categoryObject.getInt("Id"));
                    storyCategoryBean.setName(categoryObject.getString("Name"));
                    storyCategoryBean.setImageName(categoryObject.getString("ImageName"));
                    storyCategoryBean.setLanguage(categoryObject.getString("Language"));
                    storyCategoryBean.setCount(categoryObject.getInt("Count"));

                    JSONObject authorObject = storyObject.getJSONObject("Author");
                    Author_Bean authorBean = new Author_Bean();
                    authorBean.setId(authorObject.getInt("Id"));
                    authorBean.setName(authorObject.getString("Name"));

                    Story_Bean storyBean = new Story_Bean();
                    storyBean.setStoryID(storyObject.getInt("Id"));
                    storyBean.setCategory(storyCategoryBean);
                    storyBean.setAuthor(authorBean);
                    storyBean.setTitle(storyObject.getString("Title"));
                    storyBean.setSummery(storyObject.getString("Summery"));
                    storyBean.setFullStory(storyObject.getString("FullStory"));
                    storyBean.setRate(storyObject.getDouble("Rate"));
                    storyBean.setImageName(storyObject.getString("ImageName"));
                    storyBean.setLanguage(storyObject.getString("Language"));

                    String ackwardDate = storyObject.getString("CreatedDate");
                    Calendar calender = Calendar.getInstance();
                    String ackwardRipOff = ackwardDate.replace("/Date(", "").replace(")/", "");
                    Long timeInMillis = Long.valueOf(ackwardRipOff);
                    calender.setTimeInMillis(timeInMillis);

                    Date createdDate = calender.getTime();

                    ackwardDate = storyObject.getString("ModifiedDate");
                    calender = Calendar.getInstance();
                    ackwardRipOff = ackwardDate.replace("/Date(", "").replace(")/", "");
                    timeInMillis = Long.valueOf(ackwardRipOff);
                    calender.setTimeInMillis(timeInMillis);

                    Date modifiedDate = calender.getTime();

                    storyBean.setCreatedDate(createdDate);
                    storyBean.setModifiedDate(modifiedDate);

                    storyBean.setTotalComments(storyObject.getInt("TotalComments"));

                    storyBean.setComments(new ArrayList<Comment_Bean>());

                    if(storyBean.getTotalComments() > 0){

                        JSONArray comments_array = storyObject.getJSONArray("Comments");

                        for(int j = 0; j < comments_array.length(); j++){
                            JSONObject comment = comments_array.getJSONObject(j);

                            JSONObject userObject = comment.getJSONObject("User");
                            User_Bean user = new User_Bean();
                            user.setId(userObject.getInt("Id"));
                            user.setName(userObject.getString("Name"));
                            user.setEmail(userObject.getString("Email"));
                            user.setImageName(userObject.getString("ImageName"));

                            try{
                                URL userImageUrl = new URL("http://www.assets.ezcim.com/story/userpics/profilepic/"+user.getImageName());
                                Bitmap userImageBmp = BitmapFactory.decodeStream(userImageUrl.openConnection().getInputStream());
                                user.setImage(userImageBmp);
                            }catch (Exception ex){
                                user.setImage(CommonDataHolder.DefaultUserImage);
                            }

                            Comment_Bean commentBean = new Comment_Bean();
                            commentBean.setId(comment.getInt("Id"));
                            commentBean.setUser(user);
                            commentBean.setStory(new Story_Bean());
                            commentBean.setComment(comment.getString("Comment"));
                            commentBean.setRate(comment.getDouble("Rate"));

                            ackwardDate = comment.getString("CreatedDate");
                            calender = Calendar.getInstance();
                            ackwardRipOff = ackwardDate.replace("/Date(", "").replace(")/", "");
                            timeInMillis = Long.valueOf(ackwardRipOff);
                            calender.setTimeInMillis(timeInMillis);

                            commentBean.setCreatedDate(calender.getTime());

                            ackwardDate = comment.getString("ModifiedDate");
                            calender = Calendar.getInstance();
                            ackwardRipOff = ackwardDate.replace("/Date(", "").replace(")/", "");
                            timeInMillis = Long.valueOf(ackwardRipOff);
                            calender.setTimeInMillis(timeInMillis);

                            commentBean.setModifiedDate(calender.getTime());

                            commentBean.setStatus(comment.getInt("Status"));

                            storyBean.getComments().add(commentBean);
                        }

                    }

                    //URL url = new URL("http://www.assets.ezcim.com/story/storypics/storypic/story.jpg");
                    //Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                    try{
                        URL storyImageUrl = new URL("http://www.assets.ezcim.com/story/storypics/storypic/"+storyBean.getImageName());
                        Bitmap storyImageBmp = BitmapFactory.decodeStream(storyImageUrl.openConnection().getInputStream());

                        storyBean.setImage(storyImageBmp);
                    }catch (Exception ex){
                        storyBean.setImage(CommonDataHolder.DefaultStoryImage);
                    }

                    storyList.add(storyBean);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading! Please Wait.");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

    }

    @Override
    protected void onPostExecute(final Story_Bean story_bean) {
        super.onPostExecute(story_bean);

        CommonDataHolder.lstStories = storyList;

        if(storyList.size() > 0) {

            StoryListAdapter storyListAdapter = new StoryListAdapter(context, storyList);
            gridView.setAdapter(storyListAdapter);

            if (fragment != null) {

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        CommonDataHolder.selectedStoryBean = storyList.get(position);

                        Fragment storySummeryFragment = new StorySummeryFragment();
                        FragmentTransaction transaction = fragment.getFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_container, storySummeryFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });
            } else {
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CommonDataHolder.selectedStoryBean = storyList.get(position);

                        Intent i = new Intent(context, StorySummeryActivity.class);
                        context.startActivity(i);
                    }
                });
            }
        }else{
            this.gridView.setVisibility(View.GONE);
            this.rlEmptyCategory.setVisibility(View.VISIBLE);
        }

        pDialog.dismiss();
    }
}
